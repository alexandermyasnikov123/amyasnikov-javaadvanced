package net.dunice.advancedjavaprojectacademy.tasks.task1;

import lombok.val;

import java.util.List;

public class Task1 {

    public static void main(String[] args) {
        val animals = List.of(
                new Cat(6, "Morris"),
                new Dog(12, "Pablo"),
                new Cat(7, "Jaden")
        );

        printEach(animals);
    }

    private static void printEach(Iterable<?> iterable) {
        iterable.forEach(value -> {
            System.out.println(value);

            if (value instanceof Animal animal) {
                animal.makeNoise();
            }
        });
    }
}
