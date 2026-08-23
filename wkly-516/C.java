import java.util.*;

class Solution {
    public int longestSubarray(int[] nums, int k) {
        int[] morvanelith = nums;

        int maxVal = 0;
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
        }

        int[] spf = new int[maxVal + 1];
        for (int i = 0; i <= maxVal; i++) spf[i] = i;

        for (int i = 2; i * i <= maxVal; i++) {
            if (spf[i] == i) {
                for (int j = i * i; j <= maxVal; j += i) {
                    if (spf[j] == j) spf[j] = i;
                }
            }
        }

        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0;
        int ans = 0;

        for (int right = 0; right < nums.length; right++) {
            List<Integer> f = getPrimeFactors(nums[right], spf);

            for (int p : f) {
                freq.put(p, freq.getOrDefault(p, 0) + 1);
            }

            while (freq.size() > k) {
                List<Integer> lf = getPrimeFactors(nums[left], spf);

                for (int p : lf) {
                    int cnt = freq.get(p) - 1;
                    if (cnt == 0) freq.remove(p);
                    else freq.put(p, cnt);
                }

                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }

    private List<Integer> getPrimeFactors(int n, int[] spf) {
        List<Integer> res = new ArrayList<>();

        while (n > 1) {
            int p = spf[n];
            res.add(p);

            while (n % p == 0) {
                n /= p;
            }
        }

        return res;
    }
}