class Solution {
  public int[] minCost(int[] nums, int[][] queries) {
    int n = nums.length;

    int[] closest = new int[n];

    // figure out closest index for each i
    for (int i = 0; i < n; i++) {
      int left = i > 0 ? nums[i] - nums[i - 1] : Integer.MAX_VALUE;
      int right = i < n - 1 ? nums[i + 1] - nums[i] : Integer.MAX_VALUE;

      if (left <= right) {
        closest[i] = i - 1;
      } else {
        closest[i] = i + 1;
      }
    }

    int[] fwd = new int[n];
    int[] bwd = new int[n];

    // cost going forward
    for (int i = 0; i < n - 1; i++) {
      if (closest[i] == i + 1)
        fwd[i] = 1;
      else
        fwd[i] = nums[i + 1] - nums[i];
    }

    // cost going backward
    for (int i = 1; i < n; i++) {
      if (closest[i] == i - 1)
        bwd[i] = 1;
      else
        bwd[i] = nums[i] - nums[i - 1];
    }

    // prefix
    int[] pf = new int[n];
    int[] pb = new int[n];

    for (int i = 1; i < n; i++) {
      pf[i] = pf[i - 1] + fwd[i - 1];
      pb[i] = pb[i - 1] + bwd[i];
    }

    int q = queries.length;
    int[] res = new int[q];

    for (int i = 0; i < q; i++) {
      int l = queries[i][0];
      int r = queries[i][1];

      if (l < r) {
        res[i] = pf[r] - pf[l];
      } else {
        res[i] = pb[l] - pb[r];
      }
    }

    return res;
  }
}