class Solution {
    public long rob(int[] nums, int[] colors) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        long prev2 = 0;
        long prev1 = nums[0];

        for (int i = 1; i < n; i++) {

            long takeVal;

            if (colors[i] == colors[i - 1]) {
                takeVal = nums[i] + prev2;
            } else {
                takeVal = nums[i] + prev1;
            }

            long curr = Math.max(prev1, takeVal);

            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }
}
