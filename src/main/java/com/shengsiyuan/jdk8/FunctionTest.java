package com.shengsiyuan.jdk8;

import java.util.function.Function;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/4/5 0:48
 * Description:
 */
public class FunctionTest {
    public static void main(String[] args) {
        FunctionTest test = new FunctionTest();
        System.out.println(test.compute(1, var -> {
            return 2 * var;
        }));
        System.out.println(test.compute(2, var -> 5 + var));
        System.out.println(test.convert(3, var->String.valueOf(Math.pow(var, 2))));
    }

    public int compute(int a, Function<Integer, Integer> function) {
        return function.apply(a);
    }

    public String convert(int a, Function<Integer, String> function) {
        return function.apply(a);
    }
}
