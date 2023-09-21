package Multithread;

import java.util.Vector;

public class VectorMultiplier1 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int N = 10; // количество элементов вектора
        double multiplier = 2.0; // число, на которое будут умножаться элементы
        Vector<Double> vector = new Vector<>();

        for (int i = 0; i < N; i++) vector.add((double) i);

        for (int i = 0; i < N; i++) vector.set(i, vector.get(i) * multiplier);

        // System.out.println(vector);
        System.out.println("Время выполнения программы: " + (System.currentTimeMillis() - startTime) + " миллисекунд");
    }
}
