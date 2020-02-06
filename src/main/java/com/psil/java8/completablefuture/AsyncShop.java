package com.psil.java8.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static com.psil.java8.completablefuture.Util.delay;

public class AsyncShop{

    private String name;
    private Random random;

    public AsyncShop(String name) {
        this.name = name;
        random = new Random( name.charAt(0) * name.charAt(1) * name.charAt(1));
    }

    public Future<Double> getPriceAsync(String product){
        return CompletableFuture.supplyAsync( () -> calculatePrice(product));
    }

    public double calculatePrice(String product){
        delay();
        return Util.format( random.nextDouble() * product.charAt(0) + product.charAt(1));
    }
}
