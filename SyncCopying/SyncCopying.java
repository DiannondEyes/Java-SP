package SyncCopying;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SyncCopying {
    public static void main(String[] args) {
        try {
            long startTime = System.currentTimeMillis();
            Files.copy(Path.of("FileCopying\\100KBImage.jpg"), Path.of("SyncCopying\\New100KBImage.jpg"));
            Files.copy(Path.of("FileCopying\\200KBImage.jpg"), Path.of("SyncCopying\\New200KBImage.jpg"));
            System.out.println("Время выполнения программы: " + (System.currentTimeMillis() - startTime) + " мс");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}