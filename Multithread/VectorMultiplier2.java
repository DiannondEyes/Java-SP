package Multithread;

import java.util.Vector;

public class VectorMultiplier2 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int M = 10; // Число потоков
        int N = 100000; // Количество элементов в векторе
        Vector<Integer> vector = new Vector<>();
        for (int i = 1; i <= N; i++) {
            vector.add(i);
        }
        int numElements = vector.size() / M;

        Thread[] threads = new Thread[M];
        for (int i = 0; i < M; i++) {
            int startIndex = i * numElements; // startIndex вычисляется как i * numElements, где i - индекс текущего потока.
            int endIndex = (i == M - 1) ? vector.size() : (i + 1) * numElements; // Если текущий поток последний, то endIndex будет равен длине вектора. В противном случае, endIndex будет равен (i + 1) * numElements.
            threads[i] = new Thread(new VectorProcessingTask(vector, startIndex, endIndex));
            threads[i].start();
        }
        for (int i = 0; i < M; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // System.out.println("Результат обработки вектора: " + vector);
        System.out.println("Время выполнения программы: " + (System.currentTimeMillis() - startTime) + " миллисекунд");
    }
}

class VectorProcessingTask implements Runnable {
    private Vector<Integer> vector;
    private int startIndex, endIndex;

    public VectorProcessingTask(Vector<Integer> vector, int startIndex, int endIndex) {
        this.vector = vector;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void run() {
        for (int i = startIndex; i < endIndex; i++) {
            vector.set(i, vector.get(i) * 2);
        }
    }
}