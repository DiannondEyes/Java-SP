import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Laba3 {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        int minMultiple37 = Integer.MAX_VALUE;
        int maxMultiple73 = Integer.MIN_VALUE;
        int pairCount = 0;
        int minSum = Integer.MAX_VALUE;

        try (BufferedReader reader = new BufferedReader(new FileReader("17-342.txt"))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                if (count == 0) {
                    int size = Integer.parseInt(line);
                    numbers = new ArrayList<>(size);
                } else {
                    numbers.add(Integer.parseInt(line));
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int number : numbers) {
            if (number % 37 == 0 && number < minMultiple37) {
                minMultiple37 = number;
            }
            if (number % 73 == 0 && number > maxMultiple73) {
                maxMultiple73 = number;
            }
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
