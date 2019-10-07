package com.shengsiyuan.jdk8.stream;

import java.util.IntSummaryStatistics;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/4/7 22:03
 * Description:
 */
public class StreamTest5 {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.iterate(1, item -> item + 2).limit(6);
//        stream.filter(item -> item > 2).mapToInt(item -> item * 2)
//                .skip(2).limit(2).min().ifPresent(System.out::println);

        IntSummaryStatistics intSummaryStatistics = stream.filter(item -> item > 2).mapToInt(item -> item * 2)
                .skip(2).limit(2).summaryStatistics();
        System.out.println(intSummaryStatistics.getMax());
        System.out.println(intSummaryStatistics.getSum());
        System.out.println(intSummaryStatistics.getMin());
        System.out.println(intSummaryStatistics.getAverage());
    }
}
