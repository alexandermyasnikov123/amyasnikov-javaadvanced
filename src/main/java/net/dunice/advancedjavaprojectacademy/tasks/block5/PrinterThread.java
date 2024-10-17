package net.dunice.advancedjavaprojectacademy.tasks.block5;

import lombok.AllArgsConstructor;

import java.util.stream.IntStream;

@AllArgsConstructor
public class PrinterThread extends Thread {
    private final ThreadInfo<Object> info;

    @Override
    public void run() {
        IntStream.range(info.startInclusive(), info.endExclusive()).forEach(value -> {
            info.printer().accept(value);
            try {
                Thread.sleep(info.pause());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
