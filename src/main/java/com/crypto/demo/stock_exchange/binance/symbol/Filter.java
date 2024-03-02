package com.crypto.demo.stock_exchange.binance.symbol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

public class Filter {
    private String filterType;
    private String minPrice;
    private String maxPrice;
    private String tickSize;
}
