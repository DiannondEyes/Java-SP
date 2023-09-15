class AnimalThread extends Thread {
    private String name;
    private int priority;
    private int meters;

    public AnimalThread(String name, int priority) {
        this.name = name;
        this.priority = priority;
        this.meters = 0;
    }

    public void run() {
        while (meters < 100) {
            meters++;
            System.out.println(name + " преодолел " + meters + " метров");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + " финишировал!");
    }

    public void setMeters(int meters) {
        this.meters = meters;
    }

    public int getMeters() {
        return meters;
    }
}

public class RabbitAndTurtle {
    public static void main(String[] args) {
        AnimalThread rabbit = new AnimalThread("Кролик", Thread.MAX_PRIORITY);
        AnimalThread turtle = new AnimalThread("Черепаха", Thread.MIN_PRIORITY);

        rabbit.start();
        turtle.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Динамически меняем приоритеты потоков
        while (rabbit.getMeters() < 90 || turtle.getMeters() < 90) {
            if (rabbit.getMeters() >= 90 && turtle.getMeters() < 90) {
                rabbit.setPriority(Thread.MIN_PRIORITY);
                turtle.setPriority(Thread.MAX_PRIORITY);
            } else if (rabbit.getMeters() < 90 && turtle.getMeters() >= 90) {
                rabbit.setPriority(Thread.MAX_PRIORITY);
                turtle.setPriority(Thread.MIN_PRIORITY);
            }
        }
    }
}