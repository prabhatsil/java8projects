package com.psil.java8.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BestPriceFinder {

    private List<Shop> shops = Arrays.asList(
                                new Shop("BestBuy"),
                                new Shop("Costco"),
                                new Shop("Ebay")


                                    );

    private Executor executor = Executors.newFixedThreadPool(shops.size(), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
                t.setDaemon(true);
               return t;
        }
    });

    public List<String> findPricesSequential(String product){
       return shops.stream()
                .map( s -> s.getPrice(product))
                .collect(Collectors.toList());
    }

    public List<String> findPricesParallel(String product){
        return shops.parallelStream()
                .map( s -> s.getPrice(product))
                .collect(Collectors.toList());
    }

    public List<String> findPricesFuture(String product){
        List<CompletableFuture<String>>  priceFuture = findPricesInStream(product).collect(Collectors.<CompletableFuture<String>>toList());
        return priceFuture.stream().map( f -> f.join()).collect(Collectors.toList());
    }

    public Stream<CompletableFuture<String>> findPricesInStream(String product){
       return shops.stream()
                .map( shop -> CompletableFuture.supplyAsync(
                        () -> shop.getPrice(product), executor));
    }

    public  void printPricesStream(String product){
        long start = System.nanoTime();
        CompletableFuture[] futures = findPricesInStream(product).map( f ->f.thenAccept( s-> System.out.println(s + "( done in "+
                                                    ((System.nanoTime() - start)/1000_000)+ " msecs)"))).toArray(size -> new CompletableFuture[size]);

        CompletableFuture.allOf(futures).join();
        System.out.println("All shops have now responded in " + ((System.nanoTime() - start) / 1_000_000) + " msecs");
    }
}
