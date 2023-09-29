package SyncCopying;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncCopying {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        long startTime = System.currentTimeMillis();

        CompletableFuture<Void> copyTask1 = CompletableFuture.runAsync(() -> {
            try {
                Files.copy(Path.of("FileCopying\\100KBImage.jpg"), Path.of("SyncCopying\\New100KBImage.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, executorService);

        CompletableFuture<Void> copyTask2 = CompletableFuture.runAsync(() -> {
            try {
                Files.copy(Path.of("FileCopying\\200KBImage.jpg"), Path.of("SyncCopying\\New200KBImage.jpg"));
            } catch (IOException e) {
                System.out.println("Ошибка при копировании файла 2: " + e.getMessage());
            }
        }, executorService);

        CompletableFuture<Void> allTasks = CompletableFuture.allOf(copyTask1, copyTask2);

        try {
            allTasks.get();
            System.out.println("Время выполнения программы: " + (System.currentTimeMillis() - startTime) + " мс");
        } catch (InterruptedException | ExecutionException e) {
            e.getStackTrace();
        }
        executorService.shutdown();
    }
}
