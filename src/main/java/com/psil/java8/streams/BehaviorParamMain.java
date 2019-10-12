package com.psil.java8.streams;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class BehaviorParamMain {



    public static void main(String[] args) {
        RedAndHeavyApple redHeavyApple = new RedAndHeavyApple();

        Apple apple1 = new Apple();
        apple1.setColor("Red");
        apple1.setWeight(300);

        Apple apple2 = new Apple();
        apple2.setColor("Green");
        apple2.setWeight(205);

        Stream<Apple> appleStream = Stream.of(apple1, apple2);

        //red and heavy apple
        /*
        filterApple(appleStream, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.getColor().equals("Red") && apple.getWeight() > 150;
            }
        }).forEach(apple -> System.out.println(apple.color));
        */
        //green and heavy apple
        /*
        filterApple(appleStream, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.getColor().equals("Green") && apple.getWeight() > 150;
            }
        }).forEach(apple -> System.out.println(apple.color));
        */
        Comparator<Apple> byWeight = new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return Integer.valueOf(a1.getWeight()).compareTo(Integer.valueOf(a2.getWeight()));
            }
        };


        //using lamda
        Comparator<Apple> byWeightLamda = (Apple a1, Apple a2)->Integer.valueOf(a1.getWeight()).compareTo(Integer.valueOf(a2.getWeight()));

        appleStream.sorted(byWeightLamda).forEach(a -> System.out.println(a.getColor()));


    }


    public static Stream<Apple> filterApple(Stream<Apple>  inventory,ApplePredicate applePredicate) {
       return inventory.filter(apple -> applePredicate.test(apple));

    }

}


class RedAndHeavyApple implements ApplePredicate{

    @Override
    public boolean test(Apple apple) {
        return apple.getColor().equals("Red") && apple.getWeight() > 150;
    }
}

class GreenAndHeavyApple implements ApplePredicate{

    @Override
    public boolean test(Apple apple) {
        return apple.getColor().equals("Green") && apple.getWeight() > 150;
    }
}