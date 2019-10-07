package com.shengsiyuan.jdk8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/4/7 0:25
 * Description:
 */
public class OptionalTest {

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setName("zhangsan");
        Employee employee2 = new Employee();
        employee2.setName("lisi");
        Company company = new Company();
        company.setName("company1");
        List<Employee> employees = Arrays.asList(employee, employee2);
        company.setEmployees(employees);
        List<Employee> list = company.getEmployees();
        Optional<Company> optional = Optional.ofNullable(company);
        System.out.println(optional.map(theCompany -> theCompany.getEmployees()).orElse(Collections.emptyList()));
    }
}
