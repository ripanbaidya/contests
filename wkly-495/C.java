import java.util.*;

class Solution {
  public int sortableIntegers(int[] nums) {
    int n = nums.length;
    int[] a = nums;

    int[] b = nums.clone();
    Arrays.sort(b);

    ArrayList<Integer> d = new ArrayList<>();
    for (int i = 1; i * i <= n; i++) {
      if (n % i == 0) {
        d.add(i);
        if (i != n / i)
          d.add(n / i);
      }
    }

    int ans = 0;

    for (int k : d) {
      boolean ok = true;

      for (int i = 0; i < n; i += k) {
        if (!check(a, b, i, k)) {
          ok = false;
          break;
        }
      }

      if (ok)
        ans += k;
    }

    return ans;
  }

  boolean check(int[] a, int[] b, int s, int len) {
    int[] pi = new int[len];

    for (int i = 1, j = 0; i < len; i++) {
      while (j > 0 && b[s + i] != b[s + j])
        j = pi[j - 1];
      if (b[s + i] == b[s + j])
        j++;
      pi[i] = j;
    }

    for (int i = 0, j = 0; i < 2 * len - 1; i++) {
      int x = a[s + (i % len)];

      while (j > 0 && x != b[s + j])
        j = pi[j - 1];
      if (x == b[s + j])
        j++;

      if (j == len)
        return true;
    }

    return false;
  }
}