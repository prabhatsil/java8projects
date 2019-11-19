package com.psil.java8.streams;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toSet;

public class StreamsGroup {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );

        groupDish(menu);
        groupByCollectingAndThen(menu);
        groupBySum(menu);
        groupDistinctCalorieTypeByDish(menu);
        partitionByDish(menu);
        partitionByAndMaxBy(menu);
        patitionPrime(20);
    }


    public enum CaloricLevel { DIET, NORMAL, FAT}

    public static void groupDish( List<Dish> menu ){

      Map<CaloricLevel, List<Dish>> dishGroupByCalorie =
                        menu.stream().collect(Collectors
                                    .groupingBy(
                                            d -> { if(d.getCalories() <=400 ) return CaloricLevel.DIET;
                                                   else if(d.getCalories() <=700 ) return CaloricLevel.FAT;
                                                   else return CaloricLevel.NORMAL;
                                            }
                                        ));
        System.out.println("dishGroupByCalorie = " + dishGroupByCalorie);

        Map<Dish.Type, List<Dish>> dishGroupByType =  menu.stream().collect( Collectors.groupingBy(d -> d.getType()));
        System.out.println("dishGroupByType = " + dishGroupByType);

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishGrpByTypeAndCalori =
        menu.stream().collect(
                Collectors.groupingBy( d ->d.getType(),
                    Collectors.groupingBy(
                            d -> { if(d.getCalories() <=400 ) return CaloricLevel.DIET;
                            else if(d.getCalories() <=700 ) return CaloricLevel.FAT;
                            else return CaloricLevel.NORMAL;
                            })
                )
        );

        System.out.println("multilevel group - dishGrpByTypeAndCalori = " + dishGrpByTypeAndCalori);

    }

    public static void groupByCollectingAndThen(List<Dish> menu ){
        Map<Dish.Type, Dish> mostCalorieByType = menu.stream().collect(
                        Collectors.groupingBy( d->d.getType(),
                                            Collectors.collectingAndThen(
                                                    Collectors.maxBy(Comparator.comparingInt(d ->d.getCalories())),
                                                    d-> d.get()))
        );

        System.out.println("mostCalorieByType = " + mostCalorieByType);
    }

    public static void groupBySum(List<Dish> menu ){
        Map<Dish.Type, Integer> totalCalorieByType = menu.stream().collect(
                    Collectors.groupingBy( d->d.getType(), Collectors.summingInt(d ->d.getCalories()))
                );

        System.out.println("totalCalorieByType = " + totalCalorieByType);
    }

    public static void groupDistinctCalorieTypeByDish(List<Dish> menu ){
        Map<Dish.Type, Set<CaloricLevel>> calroieLevelByType = menu.stream().collect(
                Collectors.groupingBy( d ->d.getType() ,Collectors.mapping(
                    d -> {
                        if(d.getCalories() <=400 ) return CaloricLevel.DIET;
                        else if(d.getCalories() <=700 )return CaloricLevel.FAT;
                        else return CaloricLevel.NORMAL;
                    }, toSet())
                )
        );

        System.out.println("calroieLevelByType = " + calroieLevelByType);
    }


    public static void partitionByDish(List<Dish> menu ){
        Map<Boolean, List<Dish>> partionedMemnu = menu.stream().collect(
                Collectors.partitioningBy(d -> d.isVegetarian()));
        System.out.println("partionedMemnu = " + partionedMemnu);

    }

    public static void partitionByAndMaxBy(List<Dish> menu){
        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = menu.stream().collect(
                    Collectors.partitioningBy(d -> d.isVegetarian(),
                            Collectors.collectingAndThen(
                                    Collectors.maxBy(
                                            Comparator.comparingInt(d -> d.getCalories())
                                    ), d-> d.get()
                            )

                    )

        );

        System.out.println("mostCaloricPartitionedByVegetarian = " + mostCaloricPartitionedByVegetarian);
    }

    public static boolean isPrime(int candidate){
       return IntStream.range(2, candidate).noneMatch( i -> candidate % i ==0);
    }

    public static void patitionPrime(int n){
        Map<Boolean, List<Integer>> primeMap =  IntStream.rangeClosed(2, n).boxed().collect(
                Collectors.partitioningBy( i -> isPrime(i))
        );

        System.out.println("primeMap = " + primeMap);
    }




}
