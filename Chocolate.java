import java.util.Scanner;

public class Chocolate {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Имеющиеся деньги: ");
        int money = in.nextInt();
        System.out.print("Цена за шоколадку: ");
        int price = in.nextInt();
        System.out.print("Количество обёрток: ");
        int wrap = in.nextInt();
        in.close();
        int wraps = money/price;
        System.out.print("Максимум шоколадок: "+ ((wraps/wrap) + wraps));
    }
}