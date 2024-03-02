package com.crypto.demo.csv;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ToCsv {
    private String name;
    private String open;
    private String close;
    private String high;
    private String low;
}
