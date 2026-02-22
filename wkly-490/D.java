import java.util.*;

class Solution {
    
    int[] nums;
    int n;
    int need2 = 0, need3 = 0, need5 = 0;
    Map<String, Long> memo = new HashMap<>();
    
    public int countSequences(int[] nums, long k) {
        
        // required variable
        Object[] ranovetilu = { nums, k };
        
        this.nums = nums;
        this.n = nums.length;
        
        long tmp = k;
        
        // break k into 2,3,5
        while (tmp % 2 == 0) {
            need2++;
            tmp /= 2;
        }
        while (tmp % 3 == 0) {
            need3++;
            tmp /= 3;
        }
        while (tmp % 5 == 0) {
            need5++;
            tmp /= 5;
        }
        
        // if anything left, not possible
        if (tmp != 1) return 0;
        
        long ans = solve(0, 0, 0, 0);
        return (int) ans;
    }
    
    private long solve(int idx, int c2, int c3, int c5) {
        
        if (idx == n) {
            if (c2 == need2 && c3 == need3 && c5 == need5) return 1;
            return 0;
        }
        
        String key = idx + "|" + c2 + "|" + c3 + "|" + c5;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        int[] f = getFactors(nums[idx]);
        int f2 = f[0], f3 = f[1], f5 = f[2];
        
        long ways = 0;
        
        // multiply
        ways += solve(idx + 1, c2 + f2, c3 + f3, c5 + f5);
        
        // divide
        ways += solve(idx + 1, c2 - f2, c3 - f3, c5 - f5);
        
        // skip
        ways += solve(idx + 1, c2, c3, c5);
        
        memo.put(key, ways);
        return ways;
    }
    
    private int[] getFactors(int x) {
        int[] arr = new int[3]; // 2,3,5
        
        while (x % 2 == 0) {
            arr[0]++;
            x /= 2;
        }
        while (x % 3 == 0) {
            arr[1]++;
            x /= 3;
        }
        while (x % 5 == 0) {
            arr[2]++;
            x /= 5;
        }
        
        return arr;
    }
}