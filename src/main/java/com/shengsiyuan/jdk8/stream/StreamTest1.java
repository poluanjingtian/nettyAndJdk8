package com.shengsiyuan.jdk8.stream;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/4/7 16:55
 * Description:
 */
public class StreamTest1 {

    public static void main(String[] args) {
        Stream<String> hello = Stream.of("hello", "world", "hello world");
        String[] myArray = {"hello", "world", "hello world"};
        Stream<String> myArray1 = Stream.of(myArray);
        Stream<String> stream = Arrays.stream(myArray);
        Stream<String> stream1 = Arrays.asList(myArray).stream();
    }
}
