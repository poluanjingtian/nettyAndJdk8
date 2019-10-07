package com.shengsiyuan.jdk8.methodreference;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/4/7 1:39
 * Description:
 */
public class MethodReferenceTest {

    public static void main(String[] args) {
        Student student1 = new Student("zhangsan", 10);
        Student student2 = new Student("lisi", 90);
        Student student3 = new Student("wangwu", 50);
        Student student4 = new Student("zhaoliu", 40);

        List<Student> students = Arrays.asList(student1, student2, student3, student4);
//        students.sort(((o1, o2) -> Student.compareStudentByScore(o1, o2)));
        students.sort((Student::compareStudentByScore));
        students.forEach(param -> System.out.println(param.getScore()));

//        students.sort(((o1, o2) -> Student.compareStudentByName(o1, o2)));
        //students.sort((Student::compareStudentByName));
//        students.forEach(param -> System.out.println(param.getName()));
//        StudentComparator studentComparator = new StudentComparator();
        students.sort(new StudentComparator()::compareStudentByScore);
        students.sort(new StudentComparator()::compareStudentByName);
        students.forEach(item-> System.out.println(item.getName()));
    }
}
