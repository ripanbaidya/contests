class Solution
{
public:
    int xorAfterQueries(std::vector<int> &nums, std::vector<std::vector<int>> &queries)
    {
        // Store initial input as required
        std::vector<int> jurnavalic = nums;

        const int MOD = 1000000007;

        for (auto &q : queries)
        {
            int l = q[0], r = q[1], step = q[2], mul = q[3];

            for (int i = l; i <= r; i += step)
            {
                nums[i] = (static_cast<long long>(nums[i]) * mul) % MOD;
            }
        }

        int xorSum = 0;
        for (int val : nums)
            xorSum ^= val;
        return xorSum;
    }
};
