package net.dunice.advancedjavaprojectacademy.tasks.block3;

import lombok.val;
import net.dunice.advancedjavaprojectacademy.tasks.Sex;
import net.dunice.advancedjavaprojectacademy.tasks.Student;

import java.util.List;
import java.util.TreeSet;

public class Block3 implements Block3Interface {
    private final int adultAge = 18;

    @Override
    public List<Student> getAgendaList(List<Student> studentList) {
        val endingAge = 27;
        return studentList.stream().filter(student -> {
            val isMale = student.sex() == Sex.MAN;
            return isMale && student.age() >= adultAge && student.age() <= endingAge;
        }).toList();
    }

    @Override
    public List<Student> getJobList(List<Student> studentList) {
        val maleEndingAge = 60;
        val femaleEndingAge = 55;
        return studentList.stream().filter(student -> {
            val endingAge = student.sex() == Sex.WOMAN ? femaleEndingAge : maleEndingAge;
            return student.age() >= adultAge && student.age() <= endingAge;
        }).toList();
    }

    @Override
    public List<String> getListSortByAlphabet(List<String> list) {
        return list.stream().sorted().toList();
    }

    @Override
    public List<String> getListWithNumbers(List<String> list) {
        val postfix = "_1";
        return list.stream().map(value -> value + postfix).toList();
    }

    @Override
    public int[] getArrayNumbers(List<String> list) {
        return list.stream().mapToInt(value -> {
            val offset = 1;
            val string = new String(value.toCharArray(), offset, value.length() - offset);
            return Integer.parseInt(string);
        }).toArray();
    }

    @Override
    public List<String> getListSortedAndDistinct(List<String> list) {
        val sorted = new TreeSet<>(list);
        return sorted.stream().toList();
    }
}
