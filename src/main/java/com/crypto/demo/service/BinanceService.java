package com.crypto.demo.service;

import com.crypto.demo.csv.CreateCsv;
import com.crypto.demo.stock_exchange.binance.Binance;
import com.crypto.demo.csv.ToCsv;
import com.crypto.demo.stock_exchange.binance.symbol.Symbol;
import com.crypto.demo.thread.MyThreadBinance;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class BinanceService {
    private static final int MAX_THREAD = 40;
    private static final int NUMBER_OF_LIST = 70;
    private static final long ONE_DAY_TO_EPOCH = 86400000;
    public List<List<List<Object>>> get(long start) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.binance.com/api/v3/exchangeInfo"))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        Binance binance = gson.fromJson(getResponse.body(), Binance.class);

        List<List<List<Object>>> result = new ArrayList<>();
        List<Symbol> symbols = binance.getSymbols();
        List<ToCsv> list = new ArrayList<>();
        MyThreadBinance myThreadBinance[] = new MyThreadBinance[MAX_THREAD];

        for (int i = 0; i < MAX_THREAD; i++) {
            myThreadBinance[i] = new MyThreadBinance(
                    i * NUMBER_OF_LIST,
                    i * NUMBER_OF_LIST + NUMBER_OF_LIST,
                    result,
                    symbols,
                    list,
                    start,
                    Math.min(start + ONE_DAY_TO_EPOCH - 1000, System.currentTimeMillis()));
            myThreadBinance[i].start();
        }
        for (int i = 0; i < MAX_THREAD; i++){
            myThreadBinance[i].join();
        }
        CreateCsv.create(list);
        return result;
    }
}
