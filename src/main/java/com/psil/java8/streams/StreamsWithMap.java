package com.psil.java8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class StreamsWithMap {
    public static void main(String[] args) {
        Integer [] arr= {1, 2, 3, 4, 5};
        //find square of each number using streams
        List<Integer> intList = Arrays.asList(arr);
        intList.stream().map(n -> n*n).collect(toList()).forEach( s -> System.out.println(s));

        //find the pair between two arrays
        List<Integer> numlist1 = Arrays.asList(1,2,3);
        List<Integer> numlist2 = Arrays.asList(3, 4);
        numlist1.stream().flatMap(i -> numlist2.stream().filter( j -> (i +j) /3 ==0 )
                 .map( j -> new int[]{i, j}))
                .collect(Collectors.toList()).forEach( s -> System.out.println(s));

    }


}
