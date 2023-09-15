class Chicken implements Runnable {
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ChickenEgg.lastOutput = "Курица";
            System.out.println(ChickenEgg.lastOutput);
            try {
                Thread.sleep(30);
            }
            catch (InterruptedException e) {
                break;
            }
        }
    }
}

class Egg implements Runnable {
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ChickenEgg.lastOutput = "Яйцо";
            System.out.println(ChickenEgg.lastOutput);
            try {
                Thread.sleep(30);
            }
            catch (InterruptedException e) {
                break;
            }
        }
    }
}

public class ChickenEgg {
    public static String lastOutput = "";
    public static void main(String[] args) {
        Thread chicken = new Thread(new Chicken());
        Thread egg = new Thread(new Egg());
        chicken.start();
        egg.start();
        try {
            Thread.sleep(1000);
            chicken.interrupt();
            egg.interrupt();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(lastOutput + " появилось сначала!");
    }
}