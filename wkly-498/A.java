public class A {
  public int firstStableIndex(int[] nums, int k) {
    int n = nums.length;
    
    int[] pref = new int[n]; // all max 0..i for i
    int[] suff = new int[n]; // all min i ..n-1 for i

    pref[0] = nums[0];
    for (int i = 1; i < n; i ++) {
      pref[i] = Math.max(pref[i-1], nums[i]);
    }

    suff[n-1] = nums[n-1];
    for (int i= n-2; i >= 0; i --) {
      suff[i] = Math.min(suff[i+1], nums[i]);
    }

  
    for (int i = 0; i < n; i ++) {
      int score = pref[i] - suff[i];
      if (score <= k)
        return i;
    }

    return -1;
    
  }
}