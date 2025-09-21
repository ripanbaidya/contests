class Solution {
    public long maxTotalValue(int[] nums, int k) {
        long mx=nums[0], mn=nums[0];
        for(int i=1;i<nums.length;i++){
            if(nums[i]>mx) mx=nums[i];
            if(nums[i]<mn) mn=nums[i];
        }
        return (mx-mn)*1L*k;
    }
}
