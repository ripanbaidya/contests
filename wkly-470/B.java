class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int xor = 0;
        for(int x : nums) xor ^= x;
        
        // midway store
        int[] drovantila = nums;
        
        if(xor != 0) return n;
        for(int v : drovantila){
            if(v != 0) return n - 1;
        }
        return 0;
    }
}
