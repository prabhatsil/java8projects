package com.psil.java8.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static com.psil.java8.completablefuture.Util.delay;


public class Shop {
    private String name;
    private  Random random;

    public Shop(String name){
        this.name = name;
        random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public String getPrice(String product){
        double price = calculatePrice(product);
        return "name: "+name +", price : "+price;

    }

    public Future<Double> getPriceAsync(String product){
        CompletableFuture<Double> futurePrice = new CompletableFuture<Double>();
        new Thread( ()->{
                    double price = calculatePrice(product);
                    futurePrice.complete(price);
                }
        ).start();
        return futurePrice;

    }

    public double calculatePrice(String product){
        delay();
        return Util.format( random.nextDouble() * product.charAt(0) + product.charAt(1));
    }



}
