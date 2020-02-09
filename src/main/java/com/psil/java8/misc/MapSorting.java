package com.psil.java8.misc;

import java.util.*;
import java.util.stream.Collectors;

public class MapSorting {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("classA",10);
        map.put("classB",5);
        map.put("classC",30);
        map.put("classD",40);
        map.put("classE",20);
        System.out.println("sorted MAP : "+sortMapByValue(map));

    }

    public static Map<String, Integer> sortMapByValue(Map<String, Integer> inputMap){
            return
                inputMap.entrySet().stream()
                 .sorted(new Comparator<Map.Entry<String, Integer>>() {
                     @Override
                     public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                         return o1.getValue().compareTo(o2.getValue());
                     }
                 }).collect(Collectors.toMap(
                            e -> e.getKey(),
                            e -> e.getValue(),
                           (prev, next) -> prev, LinkedHashMap::new)

                        );

    }
}
