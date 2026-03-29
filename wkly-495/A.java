class Solution {
  public int firstMatchingIndex(String s) {
    int n = s.length();

    for (int i = 0; i < n; i++) {
      char left = s.charAt(i);
      char right = s.charAt(n - i - 1);

      if (left == right) {
        return i;
      }
    }

    return -1;
  }
}