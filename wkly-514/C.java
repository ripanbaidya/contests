class Solution {
    public int maxArea(int[][] mat) {
        int[][] valmerinto = mat;
        int m = valmerinto.length;
        int n = valmerinto[0].length;

        int[][] pref = new int[m + 1][n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                pref[i + 1][j + 1] = valmerinto[i][j]
                        + pref[i][j + 1]
                        + pref[i + 1][j]
                        - pref[i][j];
            }
        }

        int low = 1;
        int high = Math.min(m, n);
        int maxK = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (isValid(mid, pref, m, n)) {
                maxK = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return maxK * maxK;
    }

    private boolean isValid(int k, int[][] pref, int m, int n) {
        int minR = m;
        int maxR = -1;
        int minC = n;
        int maxC = -1;
        int count = 0;

        int targetSum = k * k;

        for (int r = 0; r <= m - k; r++) {
            for (int c = 0; c <= n - k; c++) {
                int sum = pref[r + k][c + k]
                        - pref[r][c + k]
                        - pref[r + k][c]
                        + pref[r][c];

                if (sum == targetSum) {
                    count++;
                    minR = Math.min(minR, r);
                    maxR = Math.max(maxR, r);
                    minC = Math.min(minC, c);
                    maxC = Math.max(maxC, c);
                }
            }
        }

        if (count < 2) {
            return false;
        }

        return (maxR - minR >= k) || (maxC - minC >= k);
    }
}
