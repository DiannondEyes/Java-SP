import java.util.Arrays;
import java.util.Comparator;

public class Laba4 {
    public static void main(String[] args) {
        int[][] results = new int[15][2];
        int[] votes = new int[(int)(Math.random() * 60)];
        for (int i = 0; i < 15; i++) {
            results[i][0] = i;
        }

        int vote;
        for (int i = 0; i < votes.length; i++) {
            vote = (int) (Math.random() * 15) + 1;
            votes[i] = vote;
            results[vote-1][1]++;
        }
        Arrays.sort(results, Comparator.comparingInt(a -> a[1]));

        for (int i = results.length - 1; i >= results.length - 3; i--) {
            System.out.println("Команда " + (results[i][0]+1) + ": " + results[i][1] + " голосов");
        }
    }
}