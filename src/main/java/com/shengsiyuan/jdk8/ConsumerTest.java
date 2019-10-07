package com.shengsiyuan.jdk8;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/9/7 17:30
 * Description:
 */
public class ConsumerTest {

    public void test(Consumer<Integer> consumer) {
        consumer.accept(100);
    }

    public static void main(String[] args) {
        ConsumerTest consumerTest = new ConsumerTest();
        Consumer<Integer> consumer = System.out::println;
        IntConsumer intConsumer = System.out::println;
        System.out.println(consumer instanceof IntConsumer);
        System.out.println(intConsumer instanceof Consumer);
        consumerTest.test(consumer);
        consumerTest.test(t -> consumer.accept(t));
        consumerTest.test(new Consumer<Integer>() {
            @Override
            public void accept(Integer value) {
                intConsumer.accept(value);
            }
        });
    }
}
