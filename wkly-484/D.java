class Solution {
    public int maximumAND(int[] nums, int k, int m) {
        int n = nums.length;
        int res = 0;

        // checking bits from high to low
        for (int b = 30; b >= 0; b--) {
            int cur = res | (1 << b);

            long[] need = new long[n];
            for (int i = 0; i < n; i++) {
                need[i] = calc(nums[i], cur);
            }

            Arrays.sort(need);
            long sum = 0;
            boolean ok = true;

            for (int i = 0; i < m; i++) {
                sum += need[i];
                if (sum > k) {
                    ok = false;
                    break;
                }
            }

            if (ok) res = cur;
        }

        return res;
    }

    // minimum increments needed so num contains all bits of mask
    private long calc(int num, int mask) {
        long val = num;
        long cost = 0;

        for (int b = 30; b >= 0; b--) {
            if (((mask >> b) & 1) == 1) {
                if (((val >> b) & 1) == 0) {
                    long low = (1L << b) - 1;
                    long add = (1L << b) - (val & low);
                    val += add;
                    cost += add;
                }
            }
        }
        return cost;
    }
}