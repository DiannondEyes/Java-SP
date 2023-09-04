public class Laba1_2 {
    public static void main(String[] args) {
        int[] numbers = new int[1000];
        int supermin = 10001;
        int min21 = 10001;
        int min3 = 10001;
        int min7 = 10001;
        
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int) (Math.random() * 10001);
            if (numbers[i] < supermin) {
                supermin = numbers[i];
            }
            if (numbers[i] % 21 == 0 && numbers[i] < min21) {
                min21 = numbers[i];
            }
            if (numbers[i] % 3 == 0 && numbers[i] < min21) {
                min3 = numbers[i];
            }
            if (numbers[i] % 7 == 0 && numbers[i] < min21) {
                min7 = numbers[i];
            }
        }

        int minimum1 = supermin * min21;
        int minimum2 = min3 * min7;
        if (minimum1 != 10001 && minimum2 != 10001) {
            System.out.println(Math.min(minimum1, minimum2));
        }
        else {
            System.out.println(-1);
        }
    }
}