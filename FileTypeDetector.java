import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileTypeDetector {
    private static Map<String, byte[]> types;

    static {
        types = new HashMap<>();
        types.put("mp3", new byte[]{0x49, 0x44, 0x33});
        types.put("png", new byte[]{(byte) 0x89, 0x50, 0x4E});
        types.put("gif", new byte[]{0x47, 0x49, 0x46});
        types.put("jpg", new byte[]{(byte) 0xFF, (byte) 0xD8, (byte) 0xFF});
    }

    public static String detectFileType(String filePath) {
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            byte[] header = new byte[3];
            int bytesRead = inputStream.read(header);

            if (bytesRead >= 3) {
                for (Map.Entry<String, byte[]> entry : types.entrySet()) {
                    if (compareBytes(header, entry.getValue())) {
                        return entry.getKey();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Неизвестно";
    }

    private static boolean compareBytes(byte[] bytes1, byte[] bytes2) {
        if (bytes1.length != bytes2.length) {
            return false;
        }

        for (int i = 0; i < bytes1.length; i++) {
            if (bytes1[i] != bytes2[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("Тип файла 100KBImage.jpg: " + detectFileType("FileCopying\\100KBImage.jpg"));
        System.out.println("Тип файла Besomorph - Army.mp3: " + detectFileType("Besomorph - Army.mp3"));
    }
}
