package net.dunice.advancedjavaprojectacademy.tasks.block5;

import lombok.val;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public class Block5 {
    private final Consumer<ThreadInfo<Integer>> threadInfoConsumer = info -> IntStream.range(
            info.startInclusive(),
            info.endExclusive()
    ).forEach(value -> {
        info.printer().accept(value);
        try {
            Thread.sleep(info.pause());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    });

    public static void main(String[] args) {
        val block5 = new Block5();
        block5.startSeveralThreadsViaRunnable();
    }

    private void startThreadViaConstructor() {
        new Thread(() -> {
            val startInclusive = 0;
            val endExclusive = 11;
            val pause = 500L;
            threadInfoConsumer.accept(new ThreadInfo<>(startInclusive, endExclusive, pause, System.out::println));
        }).start();
    }

    private void startSeveralThreadsViaRunnable() {
        Runnable firstPrinter = () -> {
            val startInclusive = 1;
            val endExclusive = 6;
            val pause = 500L;
            threadInfoConsumer.accept(new ThreadInfo<>(startInclusive, endExclusive, pause, System.out::println));
        };

        Runnable secondPrinter = () -> {
            val startInclusive = 'A';
            val endExclusive = 'E' + 1;
            val pause = 300L;
            threadInfoConsumer.accept(new ThreadInfo<>(
                    startInclusive, endExclusive, pause, value -> System.out.println((char) value.intValue()))
            );
        };

        new Thread(firstPrinter).start();
        new Thread(secondPrinter).start();
    }
}
