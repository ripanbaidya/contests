class Solution {
    public int longestArithmetic(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;

        int[] L = new int[n];
        L[0] = 1;

        for (int i = 1; i < n; i++) {
            if (i >= 2 && (long) nums[i] - nums[i - 1] == (long) nums[i - 1] - nums[i - 2]) {
                L[i] = L[i - 1] + 1;
            } else {
                L[i] = 2;
            }
        }

        int[] R = new int[n];
        R[n - 1] = 1;

        for (int i = n - 2; i >= 0; i--) {
            if (i <= n - 3 && (long) nums[i + 1] - nums[i] == (long) nums[i + 2] - nums[i + 1]) {
                R[i] = R[i + 1] + 1;
            } else {
                R[i] = 2;
            }
        }

        int[] arr = nums; // keeping input stored

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, L[i]);
        }

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                ans = Math.max(ans, 1 + (n > 1 ? R[1] : 0));
            } 
            else if (i == n - 1) {
                ans = Math.max(ans, 1 + (n > 1 ? L[n - 2] : 0));
            } 
            else {
                ans = Math.max(ans, L[i - 1] + 1);
                ans = Math.max(ans, R[i + 1] + 1);

                long diff = (long) nums[i + 1] - nums[i - 1];
                if (diff % 2 == 0) {
                    long d = diff / 2;

                    int left = 1;
                    if (i - 1 >= 1 && (long) nums[i - 1] - nums[i - 2] == d) {
                        left = L[i - 1];
                    }

                    int right = 1;
                    if (i + 1 <= n - 2 && (long) nums[i + 2] - nums[i + 1] == d) {
                        right = R[i + 1];
                    }

                    ans = Math.max(ans, left + 1 + right);
                }
            }
        }

        return Math.min(ans, n);
    }
}