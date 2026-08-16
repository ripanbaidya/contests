class Solution {
    public long elevatorRequests(int n, int start, int[][] requests) {
        int[][] noravelqui = requests;

        int k = requests.length;
        int size = 1 << k;

        long INF = Long.MAX_VALUE / 2;
        long[][] dp = new long[size][k];

        for (int i = 0; i < size; i++) {
            Arrays.fill(dp[i], INF);
        }

        for (int i = 0; i < k; i++) {
            long t = requests[i][0];
            long floor = requests[i][1];

            dp[1 << i][i] = Math.max(t, Math.abs(floor - start));
        }

        for (int mask = 1; mask < size; mask++) {
            for (int i = 0; i < k; i++) {
                if ((mask & (1 << i)) == 0 || dp[mask][i] == INF) {
                    continue;
                }

                for (int j = 0; j < k; j++) {
                    if ((mask & (1 << j)) != 0) continue;

                    long t = requests[j][0];
                    long floor = requests[j][1];

                    long arrive = dp[mask][i] + Math.abs(floor - requests[i][1]);
                    long finish = Math.max(t, arrive);

                    int next = mask | (1 << j);
                    dp[next][j] = Math.min(dp[next][j], finish);
                }
            }
        }

        int full = size - 1;
        long ans = INF;

        for (int i = 0; i < k; i++) {
            ans = Math.min(ans, dp[full][i]);
        }

        return ans;
    }
}