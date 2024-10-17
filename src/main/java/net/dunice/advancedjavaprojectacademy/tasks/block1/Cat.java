package net.dunice.advancedjavaprojectacademy.tasks.block1;

public final class Cat extends Animal {
    public static final int CAT_LIMIT_AGE = 12;

    public Cat(int age, String name) {
        super(age, name, CAT_LIMIT_AGE);
    }

    @Override
    public void makeNoise() {
        System.out.println("meow!");
    }
}
