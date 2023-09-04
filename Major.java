import java.util.HashMap;
import java.util.Map;

public class Major {
    public static int findMajorityElement(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int majorityElement = 0;
        int majorityCount = 0;

        for (int num : nums) {
            int count = countMap.getOrDefault(num, 0) + 1;
            countMap.put(num, count);

            if (count > majorityCount) {
                majorityElement = num;
                majorityCount = count;
            }

            if (majorityCount > nums.length / 2) {
                return majorityElement;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 2, 3, 4, 2, 1, 2, 2};
        System.out.println("Мажоритарное число: " + findMajorityElement(nums));
    }
}
