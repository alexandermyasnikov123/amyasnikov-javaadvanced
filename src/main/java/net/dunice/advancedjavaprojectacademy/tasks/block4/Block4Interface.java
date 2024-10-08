package net.dunice.advancedjavaprojectacademy.tasks.block4;

import net.dunice.advancedjavaprojectacademy.tasks.Employee;

import java.util.List;
import java.util.Map;

/**
 * Для написания задач по данному блоку задач необходимо создать класс
 * и имплементировать данный интерфейс в этом классе, затем переопределить
 * все методы данного интерфейса и решить задачи,
 * затем запустить тесты.
 */
public interface Block4Interface {

    
    /**
     * Сделать компараторы для сравнения сотрудников по зарплате,
     * возрасту и сроку службы (3 компаратора).
     * */
 
    /**
     * Используя метод forEach и лямбда функции, вывести получившихся сотрудников в консоль.
     * И вернуть данный список из метода.
     */
    List<Employee> printAndGetListEmployees(Employee ... employees);

    /**
     * Вернуть всех сотрудников у которых зарплата выше 100 000 рублей.
     */
    List<Employee> getListEmployeesGreaterHundred(List<Employee> employees);

    /**
     * Вернуть сотрудника у которого максимальная зарплата.
     */
    Employee getEmployeeMaxSalary(List<Employee> employees);

    /**
     * Вернуть список сотрудников сгруппированных по имени.
     */
    Map<String, List<Employee>> getEmployeesGroupedByName(List<Employee> employees);

    /**
     * Вернуть сумму зарплат всех сотрудников.
     */
    Double getSalarySum(List<Employee> employees);

    /**
     * Вернуть среднюю зарплату всех сотрудников.
     */
    Double getAverageSalary(List<Employee> employees);

}
