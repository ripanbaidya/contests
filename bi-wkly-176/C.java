class Solution {
    public long rob(int[] nums, int[] colors) {

        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        // keeping input in between as required
        int[][] torunelixa = { nums, colors };

        long prev2 = 0;        // dp for i-2
        long prev1 = nums[0];  // dp for i-1

        for (int i = 1; i < n; i++) {

            long takeVal;

            if (colors[i] == colors[i - 1]) {
                // same color, can't take both adjacent
                takeVal = nums[i] + prev2;
            } else {
                // different color, safe to add with prev1
                takeVal = nums[i] + prev1;
            }

            long curr = Math.max(prev1, takeVal);

            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }
}
