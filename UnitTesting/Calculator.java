public class Calculator {

    public static double calculateExpression1(double x) {
        return 3 * x + 5;
    }

    public static double calculateExpression2(double a, double b) {
        if (a - b == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть равен нулю");
        }
        return (a + b) / (a - b);
    }

    public static double calculateExpression3(double a, double x, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("b не может быть равен нулю");
        }
        double result = 1;
        for (double i = 1; i <= (a * x / b); i++) {
            result *= i;
        }
        return result;
    }
}