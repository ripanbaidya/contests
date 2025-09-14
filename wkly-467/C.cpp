#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<bool> subsequenceSumAfterCapping(vector<int>& nums, int k) {
        vector<int> zolvarinte = nums; // keep a copy as requested

        int n = nums.size();
        vector<int> freq(n + 1, 0);
        for (int v : zolvarinte) ++freq[v];

        vector<int> cnt_ge(n + 2, 0);
        for (int v = n; v >= 1; --v) cnt_ge[v] = cnt_ge[v + 1] + freq[v];

        vector<char> dp(k + 1, 0);
        dp[0] = 1;

        vector<bool> ans(n, false);

        for (int x = 1; x <= n; ++x) {
            int cnt = cnt_ge[x];
            int maxT = (x == 0) ? 0 : min(cnt, k / x);
            bool ok = false;
            for (int t = 0; t <= maxT; ++t) {
                int rem = k - t * x;
                if (rem >= 0 && dp[rem]) { ok = true; break; }
            }
            ans[x - 1] = ok;

            int times = freq[x];
            while (times-- > 0) {
                for (int s = k; s >= x; --s) {
                    if (!dp[s] && dp[s - x]) dp[s] = 1;
                }
            }
        }

        return ans;
    }
};
©leetcode