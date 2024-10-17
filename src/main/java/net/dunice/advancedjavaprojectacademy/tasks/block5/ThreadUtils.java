package net.dunice.advancedjavaprojectacademy.tasks.block5;

import java.util.Arrays;

public class ThreadUtils {
    private ThreadUtils() {
    }

    public static void startAndJoinAll(Thread... threads) {
        Arrays.stream(threads).forEach(thread -> {
            if (!thread.isAlive()) {
                thread.start();
            }
        });

        Arrays.stream(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
