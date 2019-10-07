package com.shengsiyuan.jdk8;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/4/4 23:37
 * Description:
 */
public class StringComparator {
    public static void main(String[] args) {
        List<String> names = Lists.newArrayList("zhangsan", "lisi", "wangwu", "zhaoliu");
//        Collections.sort(names, Comparator.reverseOrder());
        Collections.sort(names, (o1, o2)-> o2.compareTo(o1)
        );
        System.out.println(names);


    }
}
