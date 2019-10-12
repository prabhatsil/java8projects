package com.psil.java8.lamda;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface BuffereredReaderProcessor {
    public String process(BufferedReader br) throws IOException;
}
