package com.shengsiyuan.jdk8.stream;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/4/7 17:14
 * Description:
 */
public class StreamTest2 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        Integer sum = list.stream().map(integer -> 2 * integer).reduce(0, Integer::sum);
        System.out.println(sum);
    }
}
