import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LongestX {
    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new FileReader("24_demo.txt"))) {
            String line = r.readLine();
            int max = 1;
            int current = 1;
            for (int i = 0; i < line.length() - 1; i++) {
                if (line.charAt(i) == 'X' && line.charAt(i+1) == 'X') {
                    current++;
                    if (current > max) max = current;
                }
                else current = 1;
            }
            System.out.println("Результат: "+ Math.max(current, max));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}