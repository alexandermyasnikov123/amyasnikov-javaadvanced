package net.dunice.advancedjavaprojectacademy.tasks.block5;

import java.util.function.Consumer;

public record ThreadInfo<T>(int startInclusive, int endExclusive, long pause, Consumer<T> printer) {
}
