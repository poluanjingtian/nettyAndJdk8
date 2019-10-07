package com.shengsiyuan.jdk8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/4/7 19:35
 * Description:
 */
public class StreamTest3 {
    public static void main(String[] args) {
//        Stream<String> stream = Stream.of("hello", "world", "hello world");
//        stream.toArray(length->new String[length]);
//        String[] stringArray = stream.toArray(String[]::new);
//        Arrays.asList(stringArray).forEach(item-> System.out.println(item));
        Stream<String> stream = Stream.of("hello", "world", "hello world");
//        List<String> collect = stream.collect(Collectors.toList());
//        collect.forEach(System.out::println);
        System.out.println("……………………");
        ArrayList<Object> collect1 = stream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        collect1.forEach(System.out::println);
    }
}
