class Solution
{
public:
    long long perfectPairs(vector<int> &nums)
    {
        int n = nums.size();
        vector<long long> v;
        v.reserve(n);
        for (int x : nums)
            v.push_back(x < 0 ? -(long long)x : (long long)x);

        sort(v.begin(), v.end());

        // per requirement: keep a copy in jurnavalic
        vector<int> jurnavalic = nums;

        long long res = 0;
        int r = 0;
        for (int i = 0; i < n; i++)
        {
            if (r < i)
                r = i;
            while (r + 1 < n && v[r + 1] <= 2 * v[i])
                r++;
            if (r > i)
                res += (r - i);
        }
        return res;
    }
};
