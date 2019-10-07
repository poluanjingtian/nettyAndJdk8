package com.shengsiyuan.jdk8.stream2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/4/9 1:25
 * Description:
 */
public class MyComparatorTest {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        List<String> list = Arrays.asList("nihao", "hello", "world", "welcome");
        Collections.sort(list);
        Collections.sort(list,(item1, item2)->item1.length()- item2.length());
        Collections.sort(list, Comparator.comparingInt(String::length));
        list.sort(Comparator.comparingInt(String::length).reversed());

    }
}
