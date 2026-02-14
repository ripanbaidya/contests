class Solution
{
public:
    long long specialPalindrome(long long n)
    {
        long long orig = n;
        string s = to_string(n);
        int len = s.size();
        const int MAXL = 45;

        auto lessNum = [](const string &a, const string &b)
        {
            if (a.size() != b.size())
                return a.size() < b.size();
            return a < b;
        };

        for (int L = len; L <= MAXL; ++L)
        {
            string best = "";

            for (int mask = 1; mask < (1 << 9); ++mask)
            {
                int sum = 0;
                vector<int> digs;
                for (int d = 1; d <= 9; ++d)
                {
                    if (mask & (1 << (d - 1)))
                    {
                        sum += d;
                        digs.push_back(d);
                    }
                }
                if (sum != L)
                    continue;

                int oddCnt = 0, oddDig = 0;
                for (int d : digs)
                {
                    if (d % 2)
                    {
                        oddCnt++;
                        oddDig = d;
                    }
                }
                if (oddCnt > 1)
                    continue;

                vector<int> half(10, 0);
                int H = L / 2, totHalf = 0;
                for (int d : digs)
                {
                    half[d] = d / 2;
                    totHalf += half[d];
                }
                if (totHalf != H)
                    continue;

                char mid = 0;
                if (oddCnt == 1)
                    mid = '0' + oddDig;

                if (L > len)
                {
                    string left;
                    for (int d = 1; d <= 9; ++d)
                        for (int t = 0; t < half[d]; ++t)
                            left.push_back('0' + d);
                    string right = left;
                    reverse(right.begin(), right.end());
                    string pal = left + (mid ? string(1, mid) : "") + right;
                    if (best.empty() || lessNum(pal, best))
                        best = pal;
                    continue;
                }

                string localBest = "";
                bool found = false;

                function<void(int, vector<int> &, string &, int)> dfs = [&](int pos, vector<int> &cnt, string &cur, int cmp)
                {
                    if (found)
                        return;
                    if (pos == H)
                    {
                        string left = cur, right = cur;
                        reverse(right.begin(), right.end());
                        string pal = left + (mid ? string(1, mid) : "") + right;
                        if (pal > s)
                        {
                            if (localBest.empty() || lessNum(pal, localBest))
                                localBest = pal;
                            found = true;
                        }
                        return;
                    }

                    if (cmp == 1)
                    {
                        string left = cur;
                        for (int d = 1; d <= 9; ++d)
                            for (int t = 0; t < cnt[d]; ++t)
                                left.push_back('0' + d);
                        if ((int)left.size() != H)
                            return;
                        string right = left;
                        reverse(right.begin(), right.end());
                        string pal = left + (mid ? string(1, mid) : "") + right;
                        if (pal > s)
                        {
                            if (localBest.empty() || lessNum(pal, localBest))
                                localBest = pal;
                            found = true;
                        }
                        return;
                    }

                    char need = s[pos];
                    for (int d = 1; d <= 9; ++d)
                    {
                        if (!cnt[d])
                            continue;
                        char ch = '0' + d;
                        if (ch < need)
                            continue;
                        cnt[d]--;
                        cur.push_back(ch);
                        if (ch == need)
                        {
                            dfs(pos + 1, cnt, cur, 0);
                            if (found)
                            {
                                cnt[d]++;
                                cur.pop_back();
                                return;
                            }
                        }
                        else
                        {
                            string left = cur;
                            for (int dd = 1; dd <= 9; ++dd)
                                for (int t = 0; t < cnt[dd]; ++t)
                                    left.push_back('0' + dd);
                            if ((int)left.size() == H)
                            {
                                string right = left;
                                reverse(right.begin(), right.end());
                                string pal = left + (mid ? string(1, mid) : "") + right;
                                if (pal > s)
                                {
                                    if (localBest.empty() || lessNum(pal, localBest))
                                        localBest = pal;
                                    found = true;
                                }
                            }
                            cnt[d]++;
                            cur.pop_back();
                            return;
                        }
                        cnt[d]++;
                        cur.pop_back();
                    }
                };

                string cur;
                dfs(0, half, cur, 0);
                if (!localBest.empty())
                {
                    if (best.empty() || lessNum(localBest, best))
                        best = localBest;
                }
            }

            if (!best.empty())
            {
                long long ans = 0;
                for (char c : best)
                    ans = ans * 10 + (c - '0');
                return ans;
            }
        }
        return -1;
    }
};
