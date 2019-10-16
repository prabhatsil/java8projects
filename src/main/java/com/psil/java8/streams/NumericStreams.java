package com.psil.java8.streams;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericStreams {

    public static void main(String[] args) {


        fibonacciUsingStream(10);
        generatePythagorianTuple();
        uniqueWordsinFile();
        fibonacciTupleUsingStreamIterate();
        streamGenerate();

    }

    public static void fibonacciUsingStream(int n){
        int []f = new int [n];
        f[0] = 0;
        f[1] = 1;

        IntStream.range(2, 10).mapToObj(i -> new int []{ f[i] = f[i -1] + f[i -2] } ).forEach(s -> System.out.println(s[0]));
    }

    public static void generatePythagorianTuple(){
      Stream<double[]> pythagorianTuple = IntStream.rangeClosed(1, 100).boxed()
                                .flatMap( a -> IntStream.rangeClosed(a, 100)
                                        .mapToObj(b -> new double[]{ a, b, Math.sqrt( a*a + b*b)})
                                        .filter( t ->t[2] %1 ==0)
                                        );

        pythagorianTuple.limit(5).forEach( t -> System.out.println((int)t[0]+","+(int)t[1]+","+(int)t[2]));

    }

    public static void uniqueWordsinFile(){
        try {
            long count = Files.lines(Paths.get("/Users/psil/Documents/docs/intellj-workspace/java8projects/src/main/java/com/psil/java8/streams/test.txt"))
                                   .map( s -> (s.split(" "))).flatMap( s -> Arrays.stream(s)).distinct().count();

            System.out.println("no of unique words in a file  - "+count);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void fibonacciTupleUsingStreamIterate(){
        System.out.println("-------- fibonacciTupleUsingStreamIterate --------");
        Stream.iterate( new int[]{0, 1} , t -> new int[]{ t[1], t[0] + t[1]})
                .limit(10).forEach(t -> System.out.println("("+t[0]+", " +t[1]+")"));
    }

    public static void streamGenerate(){

        System.out.println("--------- generate stream of factorial numbers ----------");
        IntStream.generate(new IntSupplier() {
            int i =1;
            @Override
            public int getAsInt() {
                int s = 1;
                for( int k =1;k<=i;k++){
                    s = s * k;
                }
                i = i + 1;
                return  s;
            }
        }).limit(5).forEach( i -> System.out.println(i));


    }
}
