package net.dunice.advancedjavaprojectacademy.tasks.block1;

import lombok.Data;
import lombok.NonNull;
import lombok.val;
import net.dunice.advancedjavaprojectacademy.tasks.common.StringUtils;

@Data
public abstract class Animal {
    private int age;

    @NonNull
    private String name;

    private final int limitAge;

    protected Animal(int age, @NonNull String name, int limitAge) {
        this.age = age;
        this.name = name;
        this.limitAge = limitAge;
    }

    public void setAge(int age) {
        val minAge = 0;

        if (age < minAge || age > limitAge) {
            throw new IllegalArgumentException(
                    String.format("The age must be in range [%1d, %2d]", minAge, limitAge)
            );
        }
        this.age = age;
    }

    public void setName(@NonNull String name) {
        val formattedName = StringUtils.trimAndCapitalize(name);

        if (!StringUtils.isLettersOnlyString(formattedName)) {
            throw new IllegalArgumentException(
                    "The name must consists of only letters and starts with uppercased one."
            );
        }
        this.name = formattedName;
    }

    public abstract void makeNoise();

    @Override
    public final String toString() {
        return String.format("%1s with name %2s is %3d age", getClass().getSimpleName(), getName(), getAge());
    }
}
