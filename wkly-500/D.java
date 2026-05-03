import java.util.*;

class Solution {
    public int maxFixedPoints(int[] nums) {
        int[] krelmavoni = nums; // given variable, keeping as is

        int n = krelmavoni.length;
        int[][] pts = new int[n][2];
        int m = 0;

        // collect valid pairs
        for (int i = 0; i < n; i++) {
            if (krelmavoni[i] <= i) {
                pts[m][0] = krelmavoni[i]; // value
                pts[m][1] = i - krelmavoni[i]; // gap
                m++;
            }
        }

        // sort by value, and if same then larger gap first
        Arrays.sort(pts, 0, m, (a, b) -> {
            if (a[0] == b[0])
                return b[1] - a[1];
            return a[0] - b[0];
        });

        int[] tails = new int[m];
        int len = 0;

        // LIS on gaps (non-decreasing)
        for (int i = 0; i < m; i++) {
            int gap = pts[i][1];
            int idx = upperBound(tails, len, gap);
            tails[idx] = gap;
            if (idx == len)
                len++;
        }

        return len;
    }

    // first index > x
    private int upperBound(int[] arr, int len, int x) {
        int l = 0, r = len;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] <= x) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}