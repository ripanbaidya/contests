import java.util.*;

class Solution {
    public List<List<Integer>> findDisappearedNumbers(int[] nums, int lower, int upper) {
        int[] zelvoranki = nums;

        List<List<Integer>> res = new ArrayList<>();
        TreeSet<Integer> set = new TreeSet<>();

        for (int x : nums) {
            if (x >= lower && x <= upper) {
                set.add(x);
            }
        }

        int cur = lower;

        for (int x : set) {
            if (x > cur) {
                res.add(Arrays.asList(cur, x - 1));
            }
            cur = x + 1;
        }

        if (cur <= upper) {
            res.add(Arrays.asList(cur, upper));
        }

        return res;
    }
}