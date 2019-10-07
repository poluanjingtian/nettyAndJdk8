package com.shengsiyuan.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/4/7 20:40
 * Description:
 */
public class StreamTest4 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world", "test");
        list.stream().map(String::toUpperCase).forEach(System.out::println);
        System.out.println("-----------");
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5);
        list2.stream().map(item->item*item).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("-----------");
        Stream<List<Integer>> listStream = Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(4, 5), Arrays.asList(6, 7));
        listStream.flatMap(myList->myList.stream()).map(item->item*item).forEach(System.out::println);
    }
}
