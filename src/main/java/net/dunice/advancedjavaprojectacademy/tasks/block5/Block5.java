package net.dunice.advancedjavaprojectacademy.tasks.block5;

import lombok.val;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;
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
            Thread.currentThread().interrupt();
        }
    });

    public void startThreadViaConstructor() {
        val startInclusive = 1;
        val endExclusive = 11;
        val pause = 500L;
        val threadInfo = new ThreadInfo<>(startInclusive, endExclusive, pause, System.out::println);
        val thread = new PrinterThread(threadInfo);
        ThreadUtils.startAndJoinAll(thread);
    }

    public void startSeveralThreadsViaRunnable() {
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

        ThreadUtils.startAndJoinAll(new Thread(firstPrinter), new Thread(secondPrinter));
    }

    public void startSeveralThreadsAndIncrementCounter(int repeats) {
        val startValue = new AtomicInteger();
        Runnable incrementer = () -> {
            for (int i = 0; i < repeats; ++i) {
                val value = startValue.incrementAndGet();
                System.out.printf("Thread %1s: value is %2d%n", Thread.currentThread(), value);
            }
        };
        val thread1 = new Thread(incrementer);
        val thread2 = new Thread(incrementer);
        ThreadUtils.startAndJoinAll(thread1, thread2);
        System.out.println("The result is " + startValue.get());
    }

    public void startParallelTasks() {
        val threads = 10;
        val executors = Executors.newFixedThreadPool(threads);

        val start = 0;
        val end = 2;
        val timeout = 500L;

        for (int i = 0; i < threads; ++i) {
            val taskNumber = i;
            executors.submit(() -> threadInfoConsumer.accept(new ThreadInfo<>(
                    start, end, timeout,
                    value -> System.out.printf("Task %1d%n", taskNumber))
            ));
        }
        executors.shutdown();
    }

    private Long evalFactorial() {
        val requiredNumber = 5L;
        var result = 1L;

        for (int factor = 2; factor <= requiredNumber; factor++) {
            result *= factor;
        }

        return result;
    }

    public void startTask5UsingFuture() throws ExecutionException, InterruptedException {
        Callable<Long> factorialFunc = this::evalFactorial;

        val executor = Executors.newSingleThreadExecutor();
        val future = executor.submit(factorialFunc);

        System.out.printf("Future - Factorial of 5 is %1d%n", future.get());

        executor.shutdown();
    }

    public void startTask5UsingFutureTask() {
        val factorialFutureTask = new FutureTask<>(() -> {
            val factorial = evalFactorial();
            System.out.printf("Future task - Factorial of 5 is %1d%n", factorial);
            return factorial;
        });

        ThreadUtils.startAndJoinAll(new Thread(factorialFutureTask));
    }

    public void startTask6() {
        val future = CompletableFuture.supplyAsync(this::loadRemoteData)
                .thenApplyAsync(list -> list.stream().map(String::toUpperCase).toList())
                .thenApplyAsync(this::saveDataLocallyAndReturn)
                .thenAcceptAsync(System.out::println);

        future.join();
    }

    private List<String> loadRemoteData() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return List.of("first", "seconds", "third");
    }

    private List<String> saveDataLocallyAndReturn(List<String> values) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("The data was saved " + values);
        return values;
    }
}
