import java.util.concurrent.atomic.AtomicInteger;

class Account {
    AtomicInteger balance = new AtomicInteger(0);

    void addMoney(int money) {
        balance.addAndGet(money);
    }

    void removeMoney(int money) {
        balance.addAndGet(-money);
    }

    int getMoney() {
        return balance.get();
    }

}

class WaitForMoney implements Runnable {
    private final Account bank;
    private final int need_money;
    private final int money;

    public WaitForMoney(Account bank, int need_money, int money) {
        this.bank = bank;
        this.need_money = need_money;
        this.money = money;
    }

    @Override
    public void run() {
        while (true) {
            if (bank.getMoney() >= need_money) {
                bank.removeMoney(money);
                System.out.println("Обнаружено достаточно денег, чтобы списать плату за полезные услуги! Снято " + money + " рублей!");
                break;
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class BankBalance {
    public static void main(String[] args) {
        Account bank = new Account();
        new Thread(new WaitForMoney(bank, 5000, 1000)).start();
        for (int i = 0; i < 14; i++) {
            bank.addMoney(500);
            System.out.println("Баланс: " + bank.getMoney());
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}