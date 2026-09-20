class Solution {
    public long maxValue(int[] nums) {
        int n = nums.length;

        long basePulse = 0;
        for (int i = 0; i < n; i++) {
            basePulse += (i % 2 == 0) ? nums[i] : -nums[i];
        }

        if (n <= 1) {
            return basePulse;
        }

        long maxChange = 0;

        long[] pref = new long[n + 1];
        for (int i = 0; i < n; i++) {
            long flip = (i % 2 == 0) ? -2L * nums[i] : 2L * nums[i];
            pref[i + 1] = pref[i] + flip;
        }

        long minPrefEven = pref[0];
        long minPrefOdd = Long.MAX_VALUE / 2;

        for (int r = 1; r < n; r++) {
            long signR = (r % 2 == 0) ? 1 : -1;

            long deltaREven = (1 - signR) * (long) nums[r];
            if (minPrefEven != Long.MAX_VALUE / 2) {
                maxChange = Math.max(maxChange,
                        pref[r] + deltaREven - minPrefEven);
            }

            long deltaROdd = (-1 - signR) * (long) nums[r];
            if (minPrefOdd != Long.MAX_VALUE / 2) {
                maxChange = Math.max(maxChange,
                        pref[r] + deltaROdd - minPrefOdd);
            }

            if (r % 2 == 0) {
                minPrefEven = Math.min(minPrefEven, pref[r]);
            } else {
                minPrefOdd = Math.min(minPrefOdd, pref[r]);
            }
        }

        return basePulse + maxChange;
    }
}