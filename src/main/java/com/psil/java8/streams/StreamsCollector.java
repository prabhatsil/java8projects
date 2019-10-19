package com.psil.java8.streams;

import java.nio.charset.CharacterCodingException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsCollector {

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

        findMaxCalorieDish(menu).ifPresent( d -> System.out.println("max calories dish : "+d.getName() +" , calorie : "+d.getCalories()));

        showAllStatisticsInStream(menu);
        List<Integer> numList = Arrays.asList(1,2,3,44,53,60,89);
        findCommaSeparatedNumber(numList);


        String [] strArr = {"abcdef", "abcdef",  "abcabc"};

        System.out.println("longest substring : "+  Arrays.stream(strArr).reduce( (s1,s2) -> largestCommonSubString(s1,s2)).get() );

        String a = "aaa";
        String b ="aaa";
        System.out.println( "findLUSlength = "+ findLUSlength(a, b));
    }

    public static Optional<Dish> findMaxCalorieDish(List<Dish> menu){

        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(d -> d.getCalories());

        Optional<Dish>  maxCalorieDish =  menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));
        return maxCalorieDish;
    }

    public static void showAllStatisticsInStream(List<Dish> menu){
        IntSummaryStatistics intStatistics =  menu.stream().collect(Collectors.summarizingInt(d ->d.getCalories()));
        System.out.println("total calorie : "+intStatistics.getSum());
    }

    public static void findCommaSeparatedNumber(List<Integer> numList){
        String numbers = numList.stream().map( s -> s%2==0 ?  "e"+s : "o"+s  ).collect(Collectors.joining(","));
        System.out.println(numbers);
    }

    public static String largestCommonSubString(String[] strArray){
        String str1 = strArray[0];
        String s = "";
        int maxLen = 0;
        String longSubStr = "";
        boolean isCommon = true;

        for( int i =0;i<str1.length() ; i++){
            for(int j=i+1 ;j<=str1.length();j++){
                s =  str1.substring(i, j);
                for(int k=1;k<strArray.length ;k++) {
                    isCommon = isCommon & strArray[k].contains(s);
                }
                if (isCommon) {
                    if (s.length() > maxLen) {
                        maxLen = s.length();
                        longSubStr = s;
                    }
                }

            }
        }

        System.out.println("longest substring : "+longSubStr);

        return longSubStr;
    }

    /**
     * find the largest common substring from the array of strings using java 8 streams
     * @param str1
     * @param str2
     * @return String
     */
    public static String largestCommonSubString(String str1, String str2){

       return IntStream.rangeClosed(0, str1.length()).boxed().
                flatMap( i -> IntStream.rangeClosed( i, str1.length())
                        .mapToObj( j -> str1.substring(i,j)))
                        .filter( s -> str2.contains(s))
                        .max(Comparator.comparingInt( s ->s.length())).get();
    }

    /**
     * find the largest uncommon substring of between two string using java 8 stream
     * @param a
     * @param b
     * @return
     */
    public static int findLUSlength(String a, String b){
        int usLen = 0;
        if( validateInputStr(a) && validateInputStr(b)) {
            String temp;
            if(b.length() > a.length()){
                temp = a;
                a = b;
                b = temp;
            }
            final String str1 = a;
            final String str2 = b;

          OptionalInt result =  IntStream.rangeClosed(0, str1.length()).boxed().
                    flatMap(i -> IntStream.rangeClosed(i, str1.length())
                            .mapToObj(j -> str1.substring(i, j)))
                    .filter(s -> !str2.contains(s))
                    .mapToInt(s -> s.length()).max();
            usLen =  result.isPresent() ?  result.getAsInt() : 0;
        }
        return usLen;
    }

    public static boolean validateInputStr(String str){
        String regx = "^[a-z]+$";
        Pattern p = Pattern.compile(regx);
        return  str != null && str.length()<=100 && p.matcher(str).matches();
    }

}
