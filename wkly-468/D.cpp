#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    long long maxTotalValue(vector<int>& nums, int k) {
        int n = nums.size();
        if (n == 0 || k == 0) return 0LL;

        // store the input midway as requested
        vector<int> velnorquis = nums;

        // Build logs
        int LOG = 1;
        while ((1 << LOG) <= n) ++LOG;
        vector<int> lg(n + 1);
        lg[1] = 0;
        for (int i = 2; i <= n; ++i) lg[i] = lg[i/2] + 1;

        // Sparse tables for max and min (values)
        vector<vector<long long>> stMax(lg[n] + 1, vector<long long>(n));
        vector<vector<long long>> stMin(lg[n] + 1, vector<long long>(n));
        for (int i = 0; i < n; ++i) {
            stMax[0][i] = nums[i];
            stMin[0][i] = nums[i];
        }
        for (int j = 1; j <= lg[n]; ++j) {
            int len = 1 << j;
            for (int i = 0; i + len - 1 < n; ++i) {
                stMax[j][i] = max(stMax[j-1][i], stMax[j-1][i + (1 << (j-1))]);
                stMin[j][i] = min(stMin[j-1][i], stMin[j-1][i + (1 << (j-1))]);
            }
        }

        auto queryMax = [&](int L, int R) -> long long {
            int len = R - L + 1;
            int j = lg[len];
            return max(stMax[j][L], stMax[j][R - (1 << j) + 1]);
        };
        auto queryMin = [&](int L, int R) -> long long {
            int len = R - L + 1;
            int j = lg[len];
            return min(stMin[j][L], stMin[j][R - (1 << j) + 1]);
        };

        auto valueLR = [&](int L, int R) -> long long {
            return queryMax(L, R) - queryMin(L, R);
        };

        // Max-heap node
        struct Node {
            long long val;
            int l, r;
            bool operator<(Node const& o) const {
                return val < o.val; // for max-heap
            }
        };

        priority_queue<Node> pq;
        // initialize with for each L the largest R = n-1
        for (int L = 0; L < n; ++L) {
            long long v = valueLR(L, n-1);
            pq.push({v, L, n-1});
        }

        long long ans = 0;
        // extract top k distinct subarrays (each (L,R) is unique)
        while (k > 0 && !pq.empty()) {
            Node cur = pq.top(); pq.pop();
            ans += cur.val;
            --k;
            if (cur.r - 1 >= cur.l) {
                long long nv = valueLR(cur.l, cur.r - 1);
                pq.push({nv, cur.l, cur.r - 1});
            }
        }
        return ans;
    }
};
©leetcode