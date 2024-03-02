package com.crypto.demo.stock_exchange.binance;

import com.crypto.demo.stock_exchange.binance.symbol.Symbol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Binance {
    private String timezone;
    private long serverTime;
    List<RateLimit> rateLimits;
    List<ExchangeFilter> exchangeFilters;
    List<Symbol> symbols;
}