package com.crypto.demo.csv;

import java.io.FileWriter;
import java.util.List;

public class CreateCsv {
    public static void create(List<ToCsv> list){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("name")
                .append(",").append("open")
                .append(",").append("high")
                .append(",").append("low")
                .append(",").append("close")
                .append("\n");
        for (ToCsv toCsv:list){
            stringBuilder.append(toCsv.getName())
                    .append(",").append(toCsv.getOpen())
                    .append(",").append(toCsv.getClose())
                    .append(",").append(toCsv.getHigh())
                    .append(",").append(toCsv.getLow())
                    .append("\n");
        }
        try(FileWriter writer = new FileWriter("D:\\Nhat\\price.csv")){
            writer.write(stringBuilder.toString());
            System.out.println("created");
        }catch (Exception e){

        }
    }
}
