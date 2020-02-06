package com.psil.java8.completablefuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ShopMain {
    public static void main(String[] args) {
        Shop shop = new Shop("BestBuy");
        long start = System.nanoTime();
        Future<Double> future = shop.getPriceAsync("TV");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime+" milisecond");

        try{
            double price  = future.get();
            System.out.println("price is : "+ price);
        }catch(ExecutionException | InterruptedException  e){
            e.printStackTrace();
        }
        long totalTime  = (System.nanoTime() - start)/1_00_000;
        System.out.println("Price return time - "+totalTime);

    }
}
