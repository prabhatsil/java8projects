package com.psil.java8.lamda;

import com.psil.java8.streams.Apple;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Lamda2 {

    public static void main(String[] args) {
        List<String> colors = Arrays.asList("Green","Red");
        List<Apple> appleList = map(colors, (String s) -> { Apple a= new Apple(); a.setColor(s); return a;});


        appleList.forEach(a -> System.out.println(a.getColor()));
        appleList.get(0).setWeight(40);
        appleList.get(1).setWeight(20);
        BiFunction<Apple,Apple,Integer> biFunc = (Apple a1, Apple a2) ->  { return a1.getWeight()+a2.getWeight();};
        int totWeight = biFunc.apply(appleList.get(0), appleList.get(1));

        System.out.println("total weight -> "+totWeight);

        BiFunction<Apple, Apple, Integer> c3 =
                (Apple a1, Apple a2) -> Integer.valueOf(a1.getWeight()).compareTo(Integer.valueOf(a2.getWeight()));

       // sort(appleList, (a1,a2) -> Integer.valueOf(a1.getWeight()).compareTo((Integer.valueOf(a2.getWeight()))));

        sort(appleList, Comparator.comparing(a ->a.getWeight()));
        appleList.forEach(a -> System.out.println(a.getWeight()));
        //chainning of lamda expression
        appleList.sort( Comparator.comparing( (Apple a)-> a.getWeight()).reversed());

        appleList.forEach(a -> System.out.println(a.getWeight()));

        BiFunction<String, Integer, Apple> biFuncObj = (String color, Integer weight) ->new Apple(color , weight );
        Apple apple = biFuncObj.apply("Red", 10);
        System.out.println("Color : "+apple.getColor()+", Weight : "+apple.getWeight());

        ConsInterface<String,Integer, String, String,Apple> multiArgObj = (String color, Integer weight, String country,
        String type) -> new Apple(color, weight, country, type);
        Apple apple2 =  multiArgObj.apply("Green", 20, "Mexico", "Fuji");

        Function<Integer, Integer> f = (Integer x) -> x+1;
        Function<Integer, Integer> g = (Integer x) -> x*2;
        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(1);
        System.out.println("andThen  - "+result);

        h = f.compose(g);
        result = h.apply(1);
        System.out.println("compose  - "+result);
        //transformational pipeline
        Function<String, String> addHeader = (String s) ->Letter.addHeader(s);
        Function<String, String> addFooter = addHeader.andThen((String s) ->Letter.addFooter(s));
        Function<String, String> checkSpelling  = addFooter.andThen((String s) ->Letter.checkSpelling(s));

        //String header = addHeader.apply("hello");
        String output = checkSpelling.apply("We are learning labda expression ..");

        System.out.println("output - "+output);

    }


    public static List<Apple> map(List<String> colors, Function<String,Apple> func){
        List<Apple> aList = new ArrayList<Apple>();
        for(String c : colors){
           aList.add(func.apply(c));
        }
        return aList;
    }

    public static List<Apple> sort(List<Apple> list, Comparator<Apple> compFunc){
         list.sort(compFunc);
         return list;

    }



}
