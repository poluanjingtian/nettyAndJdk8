package com.shengsiyuan.jdk8.stream2;

import java.util.stream.LongStream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/9/17 23:21
 * Description:
 */
public class ForkJoinTest {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(1, 100).sum();
        System.out.println("串行流总和" + sum + "花费时间" + (System.currentTimeMillis() - startTime) + "ms");
        System.out.println("-------------并行流测试");
        long startTime1 = System.currentTimeMillis();
        long sum1 = LongStream.rangeClosed(1, 100).parallel().sum();
        System.out.println("并行流总和" + sum1 + "花费时间" + (System.currentTimeMillis() - startTime1) + "ms");
    }
}
