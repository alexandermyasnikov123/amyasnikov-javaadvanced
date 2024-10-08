package net.dunice.advancedjavaprojectacademy.tasks.task1;

public class Dog extends Animal {
    public static final int DOG_MAX_AGE = 15;

    protected Dog(int age, String name) {
        super(age, name, DOG_MAX_AGE);
    }

    @Override
    public void makeNoise() {
        System.out.println("woof...");
    }
}
