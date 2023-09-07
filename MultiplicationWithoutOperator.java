public class MultiplicationWithoutOperator {
    public static int multiply(int a, int b) {
        if (b == 0) {
            return 0;
        } else if (b > 0) {
            return a + multiply(a, b - 1);
        } else {
            return -multiply(a, -b);
        }
    }

    public static void main(String[] args) {
        System.out.println("Результат: "+ multiply(-10, 2));
    }
}