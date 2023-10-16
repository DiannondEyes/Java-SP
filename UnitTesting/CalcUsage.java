public class CalcUsage {
    public static void main(String[] args) {
        System.out.println("Результат для примера 1: " + Calculator.calculateExpression1(2.0));
        System.out.println("Результат для примера 2: " + Calculator.calculateExpression2(4.0, 2.0));
        System.out.println("Результат для примера 2: " + Calculator.calculateExpression2(5.0, 2.0));
        System.out.println("Результат для примера 3: " + Calculator.calculateExpression3(2.0, 3.0, 5.0));
        System.out.println("Результат для примера 3: " + Calculator.calculateExpression3(4.0, 3.0, 0.0));
    }
}