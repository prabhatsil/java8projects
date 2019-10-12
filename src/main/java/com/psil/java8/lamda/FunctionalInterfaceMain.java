package com.psil.java8.lamda;

import com.psil.java8.streams.Apple;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToIntBiFunction;

public class FunctionalInterfaceMain {
    public static void main(String[] args) throws Exception{

        FunctionalInterfaceMain mainObj = new FunctionalInterfaceMain();

        String oneLine = mainObj.processFile((BufferedReader br) -> br.readLine());
        String twoLine = mainObj.processFile((BufferedReader br) -> br.readLine() + br.readLine());

        System.out.println("one line - > "+oneLine );
        System.out.println("two line -> "+twoLine);
        //test predicate
        List<String> listOfString = Arrays.asList("hello","123", "1", "");
        //filtering using predicate
        List<String> result = filter(listOfString, (String s)-> !s.isEmpty());

        //filtering streams filter by using default predicate
        listOfString.stream().filter((String s)-> !s.isEmpty()).forEach(s -> System.out.println(s));

        ToIntBiFunction<Apple, Apple> c2 =
                (Apple a1, Apple a2) -> Integer.valueOf(a1.getWeight()).compareTo(Integer.valueOf(a2.getWeight()));

        listOfString.sort((String s1, String s2) -> s1.compareTo(s2));

    }

    public String processFile(BuffereredReaderProcessor p)throws IOException {
        BufferedReader br = null;
        try {
             br =new BufferedReader(new FileReader("/Users/psil/Documents/docs/intellj-workspace/java8projects/src/main/java/com/psil/java8/lamda/data.txt"));
            return p.process(br);
        }finally {
            if (br != null ){  br.close();  }
        }

    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<T>();
        for(T l : list){
            if(p.test(l)){
                result.add(l);
            }
        }
        return result;
    }




}
