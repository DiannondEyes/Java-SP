import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SequentialCopying {
    public static void main(String[] args) {
        try {
            long startTime = System.currentTimeMillis();
            copyFileUsingStream(new File("FileCopying\\100KBImage.jpg"), new File("FileCopying\\New100KBImage.jpg"));
            copyFileUsingStream(new File("FileCopying\\200KBImage.jpg"), new File("FileCopying\\New200KBImage.jpg"));
            System.out.println("Время выполнения программы: " + (System.currentTimeMillis() - startTime) + " миллисекунд");
        } catch (IOException e) {
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
