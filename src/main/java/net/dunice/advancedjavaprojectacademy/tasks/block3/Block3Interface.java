package net.dunice.advancedjavaprojectacademy.tasks.block3;

import net.dunice.advancedjavaprojectacademy.tasks.Student;

import java.util.List;

/**
 * Для написания задач по данному блоку задач необходимо создать класс и имплементировать данный
 * интерфейс в этом классе, затем переопределить все методы данного интерфейса и решить задачи,
 * затем запустить тесты.
 */
public interface Block3Interface {

    /**
     * Раздел 1:
     * Дана коллекция класс Student (с полями name — имя, age — возраст, sex — пол),
     * например, List.of(new Student(«Вася», 16, Sex.MAN), new Student(«Петя», 23, Sex.MAN),
     * new Student(«Елена», 42, Sex.WOMEN), new Student(«Иван Иванович», 69, Sex.MAN)).
     *
     * Выбрать кому придет повестка (от 18 до 27 лет)
     * Найти кол-во потенциальных работяг
     * (т.е. от 18 лет и учитывая что женщины выходят в 55 лет, а мужчина в 60)
     */

    /**
     * Задача № 1.
     * Выбрать кому придет повестка (от 18 до 27 лет)
     */
    List<Student> getAgendaList(List<Student> studentList);


    /**
     * Задача № 2.
     *  Найти кол-во потенциальных работяг
     * (т.е. от 18 лет и учитывая что женщины выходят в 55 лет, а мужчина в 60)
     */
    List<Student> getJobList(List<Student> studentList);


    /**
     * Раздел 2:
     * Дана коллекция строк List.of(«a1», «a4», «a3», «a2», «a1», «a4»)
     */

    /**
     * Задача № 1.
     * Отсортировать коллекцию строк по алфавиту.
     */
    List<String> getListSortByAlphabet(List<String> list);

    /**
     * Добавить "_1" к каждому элементу.
     */
    List<String> getListWithNumbers(List<String> list);

    /**
     * В коллекции убрать первый символ и вернуть массив чисел (int[]).
     */
    int[] getArrayNumbers(List<String> list);

    /**
     * Отсортировать коллекцию строк по алфавиту и убрать дубликаты.
     */
    List<String> getListSortedAndDistinct(List<String> list);
}
