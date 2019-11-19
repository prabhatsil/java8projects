package com.psil.java8.streams;

import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelDataProcess {
    public static void main(String[] args) {
        System.out.println("available processors - "+Runtime.getRuntime().availableProcessors());
        ParallelDataProcess parallelDataProcess = new ParallelDataProcess();
        System.out.println("****** measureSumPerf ****** "+  parallelDataProcess.measureSumPerf( ParallelDataProcess::iteratelSum, 10_000_000 ) );
    }

    public static Long parallelSum(long n){
        Long sum = Stream.iterate(1L, i ->i+1)
                .limit(n)
                .parallel()
                .reduce(0L, (x, y)-> Long.sum(x , y));
        return sum;
    }

    public static Long optimizedParallelStream(long n){
        Long sum = LongStream.rangeClosed(1, n)
                    .parallel()
                    .reduce(0L, (x, y)-> Long.sum(x , y));
        return sum;
    }



    public  static Long iteratelSum(long n){
        Long sum = Stream.iterate(1L, i ->i+1)
                .limit(n)
                .reduce(0L, (x,y)-> Long.sum(x , y));
        return sum;
    }

    public long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for(int i=0; i<10; i++){
            long start = System.currentTimeMillis();
            long sum = adder.apply(n);
            long duration = (System.currentTimeMillis() - start);
            if(duration < fastest) fastest = duration;
        }
        return fastest;
    }


}
