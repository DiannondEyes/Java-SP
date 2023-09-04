import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Laba3 {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<Integer>();
        int minMultiple37 = Integer.MAX_VALUE;
        int maxMultiple73 = Integer.MIN_VALUE;
        int pairCount = 0;
        int minSum = Integer.MAX_VALUE;

        try (BufferedReader reader = new BufferedReader(new FileReader("17-342.txt"))) {
            String line;
            Integer number = null;
            while ((line = reader.readLine()) != null) {
                number = Integer.parseInt(line);
                numbers.add(number);
                if (number % 37 == 0 && number < minMultiple37) {
                    minMultiple37 = number;
                }
                if (number % 73 == 0 && number > maxMultiple73) {
                    maxMultiple73 = number;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for (int i = 0; i < numbers.size() - 1; i++) {
            if ((numbers.get(i) == minMultiple37 && numbers.get(i+1) != maxMultiple73) || (numbers.get(i) != minMultiple37 && numbers.get(i+1) == maxMultiple73)) {
                pairCount++;
                int sum = numbers.get(i) + numbers.get(i+1);
                if (sum < minSum) {
                    minSum = sum;
                }
            }
        }

        System.out.println("Количество найденных пар: " + pairCount);
        System.out.println("Минимальная сумма элементов среди таких пар: " + minSum);
    }
}
