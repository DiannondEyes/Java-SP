import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ParallelCopying {
    public static void main(String[] args) {
        try {
            long startTime = System.currentTimeMillis();

            Thread thread1 = new Thread(() -> {
                try {
                    copyFileUsingStream(new File("FileCopying\\100KBImage.jpg"), new File("FileCopying\\New100KBImage.jpg"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Thread thread2 = new Thread(() -> {
                try {
                    copyFileUsingStream(new File("FileCopying\\200KBImage.jpg"), new File("FileCopying\\New200KBImage.jpg"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();

            System.out.println("Время выполнения программы: " + (System.currentTimeMillis() - startTime) + " миллисекунд");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            if (is != null) is.close();
            if (os != null) os.close();
        }
    }
}
