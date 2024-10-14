package net.dunice.advancedjavaprojectacademy.tasks.block5;

import lombok.val;

import java.util.concurrent.ExecutionException;

public class Block5Launcher {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        val block5 = new Block5();
        val repeats = 12;

        block5.startTask6();

        block5.startTask5UsingFutureTask();
        block5.startTask5UsingFuture();

        block5.startParallelTasks();
        block5.startSeveralThreadsAndIncrementCounter(repeats);
        block5.startThreadViaConstructor();
        block5.startSeveralThreadsViaRunnable();
    }
}
