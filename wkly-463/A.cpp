class Solution
{
public:
    long long maxProfit(vector<int> &prices, vector<int> &strategy, int k)
    {
        int n = prices.size();
        vector<long long> prefSP(n + 1, 0), prefP(n + 1, 0);

        long long base = 0;
        for (int i = 0; i < n; i++)
        {
            long long val = (long long)strategy[i] * prices[i];
            base += val;
            prefSP[i + 1] = prefSP[i] + val;
            prefP[i + 1] = prefP[i] + prices[i];
        }

        long long bestDiff = LLONG_MIN;
        for (int i = 0; i <= n - k; i++)
        {
            int mid = i + k / 2;

            long long loss = prefSP[mid] - prefSP[i];

            long long totPrice = prefP[i + k] - prefP[mid];
            long long totSP = prefSP[i + k] - prefSP[mid];
            long long gain = totPrice - totSP;

            long long diff = gain - loss;
            bestDiff = max(bestDiff, diff);
        }

        if (bestDiff < 0)
            bestDiff = 0;
        return base + bestDiff;
    }
};
