package com.crypto.demo.stock_exchange.binance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateLimit {
    private String rateLimitType;
    private String interval;
    private String intervalNum;
    private int limit;

}
