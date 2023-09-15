class AnimalThread extends Thread {
    private String name;
    private int priority;
    private int distance;

    public AnimalThread(String name, int priority) {
        this.name = name;
        this.priority = priority;
        this.distance = 0;
        setPriority(priority);
    }

    public int getDistance() {return this.distance;}

    public void run() {
        try {
            while (distance < 100) {
                distance += 10;
                System.out.println(name + " пробежал(а) " + distance + " м.");
                Thread.sleep(100);
            }
            System.out.println(name + " финишировал(а)!");
        } catch (InterruptedException e) {
            System.out.println(name + " был(а) прерван(а).");
        }
    }
}

public class RabbitAndTurtle {
    public static void main(String[] args) {
        AnimalThread rabbit = new AnimalThread("Кролик", Thread.MIN_PRIORITY); // Запускаю с максимальным приоритетом
        AnimalThread turtle = new AnimalThread("Черепаха", Thread.MIN_PRIORITY); // Запускаю с минимальным приоритетом

        rabbit.start();
        turtle.start();

        try {
            rabbit.join();
            turtle.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Победил " + (turtle.getDistance() > rabbit.getDistance() ? "Черепаха!" : "Кролик!"));
    }
}