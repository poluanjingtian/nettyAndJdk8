package com.shengsiyuan.jdk8;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/4/7 0:45
 * Description:
 */
public class MethodReferenceDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world");
        list.forEach(item -> System.out.println(item));
        list.forEach(System.out::println);


    }
}
