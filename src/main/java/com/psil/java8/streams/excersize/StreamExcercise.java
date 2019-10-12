package com.psil.java8.streams.excersize;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamExcercise {
    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        System.out.println("------ find transaction in the year 2011 and sort by value----");
        findAllTransAndSortbyValue(transactions);
        System.out.println("---- find unique cities---");
        findUniqueCities(transactions);
        System.out.println("-------- find All Traders From CambridgeAndSortByName --------");
        findAllTradersFromCambridgeAndSortByName(transactions);
        System.out.println("-------- find All Traders Names Alphabetically --------");
        System.out.println(findAllTradersSortedAlphabetically(transactions));
        System.out.println("-------- Find Any Trader in Milan--------");
        System.out.println(findAnyTradersInMilan(transactions));
        System.out.println("--------findAllTransactionValuesFromCambridge--------");
        findAllTransactionValuesFromCambridge(transactions);
        System.out.println("-------- find max value of transaction -------");
        findMaxTransactionValue(transactions);
        System.out.println("-------- find transaction with smallest value-------");
        findTransactionWithMinValue(transactions);

    }

    public static void findAllTransAndSortbyValue(List<Transaction> transactions){
        transactions.stream().filter(t ->t.getYear() == 2011)
                             .sorted( (x,y) -> Integer.compare(x.value,y.value))
                             .forEach( t->System.out.println(t));

    }

    public static void findUniqueCities(List<Transaction> transactions){
        transactions.stream().map( t -> t.getTrader().getCity()).distinct()
                .forEach( t->System.out.println(t));
    }

    public static void findAllTradersFromCambridgeAndSortByName(List<Transaction> transactions){
        transactions.stream().filter( t -> t.getTrader().getCity().equals("Cambridge"))
                             .map( t -> t.getTrader().getName())
                             .distinct()
                             .sorted( (x,y) -> (x.compareTo(y)))
                             .forEach( s->System.out.println(s));
    }

    public static String findAllTradersSortedAlphabetically(List<Transaction> transactions){
        return transactions.stream()
                .map( t -> t.getTrader().getName())
                .distinct()
                .sorted( (x,y) -> (x.compareTo(y)))
                .reduce("", (s1,s2) -> s1 + s2);
    }


    public static boolean findAnyTradersInMilan(List<Transaction> transactions){
        Optional<Trader> optional = transactions.stream().map( t ->t.getTrader())
                .filter( t -> t.getCity().equals("Milan"))
                .findAny();
        return optional.isPresent();
    }
    public static void findAllTransactionValuesFromCambridge(List<Transaction> transactions) {
       transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(t -> t.getValue()).forEach( s->System.out.println(s));

    }

    public static void findMaxTransactionValue(List<Transaction> transactions) {
        transactions.stream().map( t-> t.getValue())
                                        .max( (x,y) -> Integer.compare(x,y)).ifPresent( s -> System.out.println(s));
    }

    public static void findTransactionWithMinValue(List<Transaction> transactions) {
        System.out.println(transactions.stream().min( (x,y) -> Integer.compare(x.getValue(), y.getValue())).get());
    }


}
