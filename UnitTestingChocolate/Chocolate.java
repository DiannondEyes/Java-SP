import java.util.Scanner;

public class Chocolate {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Имеющиеся деньги: ");
        int money = in.nextInt();
        System.out.print("Цена за шоколадку: ");
        int price = in.nextInt();
        System.out.print("Количество обёрток для бонусной шоколадки: ");
        int wrap = in.nextInt();
        in.close();

        int result = calculateMaxChocolates(money, price, wrap);

        System.out.println("Максимум шоколадок: " + result);
    }

    public static int calculateMaxChocolates(int money, int price, int wrap) {
        int chocolates = money / price;
        int wrappers = chocolates;

        while (wrappers >= wrap) {
            chocolates += (wrappers / wrap);
            wrappers = wrappers % wrap + (wrappers / wrap);
        }

        return chocolates;
    }
}
