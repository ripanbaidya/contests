class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        if(n<=2) return n;

        int maxLen=2, cur=2;
        int[] ar = nums;

        for(int i=2;i<n;i++){
            long sum = (long)ar[i-1] + ar[i-2];
            if(ar[i]==sum) cur++;
            else cur=2;
            if(cur>maxLen) maxLen=cur;
        }
        return maxLen;
    }
}
