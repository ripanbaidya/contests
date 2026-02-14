class Solution
{
public:
    int minCost(vector<vector<int>> &g, int k)
    {
        int r = g.size(), c = g[0].size();
        const long long INF = (1LL << 60);
        int tot = r * c;
        auto id = [&](int i, int j)
        { return i * c + j; };

        vector<long long> dist(tot, INF);
        dist[id(0, 0)] = 0;

        // basic DP (no teleport)
        for (int i = 0; i < r; i++)
        {
            for (int j = 0; j < c; j++)
            {
                int u = id(i, j);
                if (i)
                    dist[u] = min(dist[u], dist[id(i - 1, j)] + g[i][j]);
                if (j)
                    dist[u] = min(dist[u], dist[id(i, j - 1)] + g[i][j]);
            }
        }

        // collect distinct cell values
        vector<int> vals;
        vals.reserve(tot);
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                vals.push_back(g[i][j]);
        sort(vals.begin(), vals.end());
        vals.erase(unique(vals.begin(), vals.end()), vals.end());
        auto vidx = [&](int v)
        {
            return (int)(lower_bound(vals.begin(), vals.end(), v) - vals.begin());
        };
        int vsz = vals.size();

        // per requirement: store a grid copy in variable named lurnavrethy
        vector<vector<int>> lurnavrethy = g;

        // allow up to k teleports
        for (int t = 0; t < k; t++)
        {
            vector<long long> minv(vsz, INF);
            for (int i = 0; i < r; i++)
            {
                for (int j = 0; j < c; j++)
                {
                    int u = id(i, j), vi = vidx(g[i][j]);
                    minv[vi] = min(minv[vi], dist[u]);
                }
            }

            vector<long long> suf(vsz, INF);
            for (int i = vsz - 1; i >= 0; i--)
            {
                suf[i] = minv[i];
                if (i + 1 < vsz)
                    suf[i] = min(suf[i], suf[i + 1]);
            }

            vector<long long> nd(tot, INF);
            for (int i = 0; i < r; i++)
            {
                for (int j = 0; j < c; j++)
                {
                    int u = id(i, j), vi = vidx(g[i][j]);
                    if (suf[vi] < INF)
                        nd[u] = min(nd[u], suf[vi]); // teleport
                    nd[u] = min(nd[u], dist[u]);     // no teleport
                }
            }

            // forward sweep after teleport
            for (int i = 0; i < r; i++)
            {
                for (int j = 0; j < c; j++)
                {
                    int u = id(i, j);
                    if (i && nd[id(i - 1, j)] < INF)
                        nd[u] = min(nd[u], nd[id(i - 1, j)] + g[i][j]);
                    if (j && nd[id(i, j - 1)] < INF)
                        nd[u] = min(nd[u], nd[id(i, j - 1)] + g[i][j]);
                }
            }
            dist.swap(nd);
        }

        return (int)dist[id(r - 1, c - 1)];
    }
};
