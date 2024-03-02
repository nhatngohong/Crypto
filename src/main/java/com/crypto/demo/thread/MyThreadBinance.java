package com.crypto.demo.thread;

import com.crypto.demo.csv.ToCsv;
import com.crypto.demo.stock_exchange.binance.symbol.Symbol;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Data
@AllArgsConstructor
public class MyThreadBinance extends Thread{
    private int start;
    private int end;
    private List<List<List<Object>>> res;
    private List<Symbol> api;
    private List<ToCsv> list;
    private long startEpoch;
    private long endEpoch;
    @Override
    public synchronized void run(){

            for (int i = start; i < end ; i++){
                String name = api.get(i).getSymbol();
                try {
                    Gson gson = new Gson();
                    HttpClient httpClient = HttpClient.newHttpClient();
                    HttpRequest get = HttpRequest.newBuilder()
                            .uri(new URI("https://api.binance.com/api/v3/klines?symbol=" + name +"&interval=1d"
                                        + "&startTime=" + startEpoch + "&endTime=" + endEpoch))
                            .build();
                    HttpResponse<String> response = httpClient.send(get, HttpResponse.BodyHandlers.ofString());
                    List<List<Object>> getPrice = gson.fromJson(response.body(), List.class);
                    if (!getPrice.isEmpty()){
                        list.add(new ToCsv(
                                name,
                                getPrice.get(0).get(1).toString(),
                                getPrice.get(0).get(2).toString(),
                                getPrice.get(0).get(3).toString(),
                                getPrice.get(0).get(4).toString()));
                    }
                    res.add(getPrice);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("done");

    }
}
