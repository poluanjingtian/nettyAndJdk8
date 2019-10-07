package com.shengsiyuan.jdk8;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/4/3 1:42
 * Description:
 */
public class Test1 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world");
//        list.forEach(item-> System.out.println(item.toUpperCase()));
        list.stream().map(String::toUpperCase).forEach(System.out::println);
    }


}
