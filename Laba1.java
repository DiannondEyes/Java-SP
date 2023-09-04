public class Laba1 {
    public static void main(String[] args) {
        int[] numbers = new int[1000];
        int supermax = -1;
        int max14 = -1;
        int max2 = -1;
        int max7 = -1;
        
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int) (Math.random() * 10001);
            if (numbers[i] > supermax) {
                supermax = numbers[i];
            }
            if (numbers[i] % 14 == 0 && numbers[i] > max14) {
                max14 = numbers[i];
            }
            if (numbers[i] % 2 == 0 && numbers[i] > max14) {
                max2 = numbers[i];
            }
            if (numbers[i] % 7 == 0 && numbers[i] > max14) {
                max7 = numbers[i];
            }
        }

        int maximum1 = supermax * max14;
        int maximum2 = max2 * max7;
        if (maximum1 != 0 && maximum2 != 0) {
            System.out.println(Math.max(maximum1, maximum2));
        }
        else {
            System.out.println(-1);
        }
    }
}