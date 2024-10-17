package net.dunice.advancedjavaprojectacademy.tasks.block4;

import lombok.val;
import net.dunice.advancedjavaprojectacademy.tasks.Employee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Block4 implements Block4Interface {
    private final Comparator<Employee> salaryComparator = Comparator.comparingDouble(Employee::salary);

    private final Comparator<Employee> workYearsComparator = Comparator.comparingInt(Employee::workYears);

    @Override
    public List<Employee> printAndGetListEmployees(Employee... employees) {
        val employeesList = Arrays.stream(employees).toList();

        System.out.println("By salary:");
        employeesList.stream().sorted(salaryComparator).forEach(System.out::println);

        System.out.println("By work years:");
        employeesList.stream().sorted(workYearsComparator).forEach(System.out::println);

        return employeesList;
    }

    @Override
    public List<Employee> getListEmployeesGreaterHundred(List<Employee> employees) {
        val targetSalary = 100_000;
        return employees.stream().filter(employee -> employee.salary() > targetSalary).toList();
    }

    @Override
    public Employee getEmployeeMaxSalary(List<Employee> employees) {
        return employees.stream().max(salaryComparator).orElseThrow();
    }

    @Override
    public Map<String, List<Employee>> getEmployeesGroupedByName(List<Employee> employees) {
        return employees.stream().collect(Collectors.groupingBy(Employee::firstName));
    }

    @Override
    public Double getSalarySum(List<Employee> employees) {
        return employees.stream().mapToDouble(Employee::salary).sum();
    }

    @Override
    public Double getAverageSalary(List<Employee> employees) {
        return employees.stream().mapToDouble(Employee::salary).average().orElseThrow();
    }
}
