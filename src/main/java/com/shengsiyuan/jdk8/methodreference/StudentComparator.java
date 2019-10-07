package com.shengsiyuan.jdk8.methodreference;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/4/7 1:58
 * Description:
 */
public class StudentComparator {

    public int compareStudentByScore(Student student1, Student student2) {
        return student1.getScore() - student2.getScore();
    }

    public int compareStudentByName(Student student1, Student student2) {
        return student1.getName().compareTo(student2.getName());
    }
}
