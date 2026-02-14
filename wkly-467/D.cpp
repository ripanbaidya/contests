class Solution {
public:
    int countStableSubsequences(vector<int>& nums) {
        const int MOD = 1000000007;
        long long f[2][3] = {{0,0,0},{0,0,0}}; // f[parity][run_len]
        long long emp = 1; // empty subsequence

        vector<int> morquedrin = nums; // keep a copy as asked

        for (int x : morquedrin) {
            int cur = x & 1;
            long long g[2][3];

            // start with "not take" state
            for (int p = 0; p < 2; ++p)
                for (int r = 1; r <= 2; ++r)
                    g[p][r] = f[p][r];

            // start new from empty
            g[cur][1] = (g[cur][1] + emp) % MOD;

            // extend existing
            for (int p = 0; p < 2; ++p) {
                for (int r = 1; r <= 2; ++r) {
                    long long cnt = f[p][r];
                    if (!cnt) continue;
                    if (p == cur) {
                        if (r == 1) g[cur][2] = (g[cur][2] + cnt) % MOD;
                    } else {
                        g[cur][1] = (g[cur][1] + cnt) % MOD;
                    }
                }
            }

            // commit
            for (int p = 0; p < 2; ++p)
                for (int r = 1; r <= 2; ++r)
                    f[p][r] = g[p][r];
        }

        long long ans = 0;
        for (int p = 0; p < 2; ++p)
            for (int r = 1; r <= 2; ++r)
                ans = (ans + f[p][r]) % MOD;

        return (int)ans;
    }
};
