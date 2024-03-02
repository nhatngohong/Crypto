package com.crypto.demo.controller;

import com.crypto.demo.service.BinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
public class MainController {
    @Autowired
    private BinanceService binanceService;
    @GetMapping("/get")
    public List<List<List<Object>>> get(@RequestParam("date")
                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                        LocalDate date) throws URISyntaxException, IOException, InterruptedException {
        ZoneId zoneId = ZoneId.of("UCT");
        long epoch = date.atStartOfDay(zoneId).toInstant().toEpochMilli();
        System.out.println(epoch);
        System.out.println(Math.min(epoch + 86400000 - 1000, System.currentTimeMillis()));
        return binanceService.get(epoch);
    }
}
