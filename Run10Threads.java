public class Run10Threads implements Runnable {
    public void run() {
        System.out.println("Поток " + Thread.currentThread().getId() + " запущен.");
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            Thread thread = new Thread(new Run10Threads());
            thread.start();
        }
    }
}