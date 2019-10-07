package com.shengsiyuan.joda;

import org.joda.primitives.list.impl.ArrayIntList;
import org.joda.time.LocalDate;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/9/9 23:36
 * Description:
 */
public class JodaTest {

    public static void main(String[] args) {
        ArrayIntList list = new ArrayIntList();
        list.add(1);
        list.add(2);
        list.forEach(System.out::println);
        Calendar calendar = Calendar.getInstance();
        int firstDayOfWeek = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println(firstDayOfWeek);
        int year = LocalDate.now().getYear();
        System.out.println(year);
    }
}
