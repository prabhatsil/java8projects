package com.psil.java8.lamda;

import com.psil.java8.streams.Apple;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicLamda {


    public static void main(String[] args) {
        Apple apple1 = new Apple();
        apple1.setColor("Red");
        apple1.setWeight(300);
        List<Apple> appleList = new ArrayList<Apple>();
        appleList.add(apple1);
        boolean isEmpty =   appleList.isEmpty();

        FetchApple f = (List<Apple> list, int i) ->  {return list.get(i).getColor();};
        System.out.println("1st Apple Color : "+  f.getColor(appleList,0));

        Runnable r = () -> { System.out.println("I am running "+new Date()); };
        r.run();
        BasicLamda b = new BasicLamda();
        b.process(() -> { System.out.println("running for functional interface ");});


    }


    public void process(Runnable r){
        r.run();
    }

}

interface FetchApple{
    String getColor(List<Apple> list, int index);
}


