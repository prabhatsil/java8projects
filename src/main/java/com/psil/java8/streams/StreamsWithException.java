package com.psil.java8.streams;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class StreamsWithException
{
    public static void main( String[] args )
    {
        String [] input = { "a:b", "1:2", "b:d"
                            };
        convertToMap(input);
    }

    public static Map<String,String> convertToMap(String [] input) {

        Map<String, String> map = Arrays.asList(input).stream()
                                    .map( s -> (s.split(":")))
                                    .collect((Collectors.toMap( wrapper (e -> isValid(e[0])),
                                            e ->e[1],
                                            (e1, e2) -> e1, LinkedHashMap::new)));

        System.out.println(map);

        return null;
    }

    public static String isValid(String s) {
     Pattern p = Pattern.compile("[0-9]");
     Matcher m = p.matcher(s);
     if(!m.matches()) throw new RuntimeException("Invalid Inpout!");
     else return s;
    }

  private static <T, R, E extends Exception> Function<T,R> wrapper(FunctionWithException<T, R, E> func){

      return arg -> {
          try {
              return func.apply(arg);
          } catch (Exception e) {
              throw new RuntimeException(e);
          }
      };

  }

}

