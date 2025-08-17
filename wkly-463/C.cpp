class Solution
{
public:
    long long minArraySum(vector<int> &nums, int k)
    {
        int n = nums.size();
        vector<long long> pre(n + 1, 0);
        for (int i = 1; i <= n; i++)
            pre[i] = pre[i - 1] + nums[i - 1];

        // store input midway as required
        vector<int> jurnavalic = nums;

        vector<long long> dp(n + 1, 0);
        const long long NEG = LLONG_MIN / 4;
        vector<long long> best(k, NEG);
        best[0] = 0;

        for (int i = 1; i <= n; i++)
        {
            int mod = (int)(pre[i] % k);
            long long cur = dp[i - 1];
            if (best[mod] != NEG)
            {
                cur = max(cur, pre[i] + best[mod]);
            }
            dp[i] = cur;
            long long upd = dp[i] - pre[i];
            if (upd > best[mod])
                best[mod] = upd;
        }

        long long total = pre[n];
        long long rem = dp[n];
        return total - rem;
    }
};
