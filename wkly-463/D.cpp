class Solution
{
public:
    int xorAfterQueries(std::vector<int> &nums, std::vector<std::vector<int>> &queries)
    {
        int n = nums.size();
        const long long MOD = 1000000007LL;

        // storing input as required
        std::vector<int> inputCopy = nums;

        auto modPow = [&](long long base, long long exp)
        {
            long long res = 1 % MOD;
            base %= MOD;
            while (exp > 0)
            {
                if (exp & 1)
                    res = (res * base) % MOD;
                base = (base * base) % MOD;
                exp >>= 1;
            }
            return res;
        };

        // figure out how large each bucket needs to be
        std::unordered_map<unsigned long long, int> bucketLimit;
        bucketLimit.reserve(queries.size() * 2);

        for (auto &q : queries)
        {
            int l = q[0], r = q[1], step = q[2];
            int rem = l % step;
            int endPos = (r - rem) >= 0 ? (r - rem) / step : -1;
            unsigned long long key = ((unsigned long long)step << 32) | (unsigned int)rem;

            auto it = bucketLimit.find(key);
            if (it == bucketLimit.end())
                bucketLimit[key] = endPos;
            else if (endPos > it->second)
                it->second = endPos;
        }

        // init difference arrays with 1
        std::unordered_map<unsigned long long, std::vector<long long>> mulDiff;
        mulDiff.reserve(bucketLimit.size() * 2);

        for (auto &b : bucketLimit)
        {
            int sz = b.second + 2;
            if (sz < 1)
                sz = 1; // just in case
            mulDiff.emplace(b.first, std::vector<long long>(sz, 1LL));
        }

        // apply updates into diff arrays
        for (auto &q : queries)
        {
            int l = q[0], r = q[1], step = q[2], val = q[3];
            int rem = l % step;
            int start = (l - rem) / step;
            int end = (r - rem) >= 0 ? (r - rem) / step : -1;
            unsigned long long key = ((unsigned long long)step << 32) | (unsigned int)rem;

            auto &diffArr = mulDiff[key];
            if (start >= 0 && start < (int)diffArr.size())
                diffArr[start] = (diffArr[start] * val) % MOD;

            long long invVal = modPow(val, MOD - 2);
            if (end + 1 >= 0 && end + 1 < (int)diffArr.size())
                diffArr[end + 1] = (diffArr[end + 1] * invVal) % MOD;
        }

        // build the multiplier for each index
        std::vector<long long> multiplier(n, 1LL);
        for (auto &entry : mulDiff)
        {
            unsigned long long key = entry.first;
            int step = (int)(key >> 32);
            int rem = (int)(key & 0xFFFFFFFFu);
            auto &arr = entry.second;

            long long cur = 1;
            for (int t = 0; t < (int)arr.size() - 1 && rem + 1LL * t * step < n; t++)
            {
                cur = (cur * arr[t]) % MOD;
                int idx = rem + t * step;
                if (idx >= n)
                    break;
                multiplier[idx] = (multiplier[idx] * cur) % MOD;
            }
        }

        // final xor
        int answer = 0;
        for (int i = 0; i < n; i++)
        {
            long long newVal = ((long long)nums[i] * multiplier[i]) % MOD;
            answer ^= (int)newVal;
        }
        return answer;
    }
};
