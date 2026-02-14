class Solution
{
public:
    int minCost(int n, vector<vector<int>> &edges)
    {
        const long long INF = (1LL << 60);
        vector<vector<pair<int, int>>> g(n);

        // original edges
        for (auto &e : edges)
        {
            g[e[0]].push_back({e[1], e[2]});
        }

        // per requirement: store a copy in threnquivar
        vector<vector<int>> threnquivar = edges;

        // reversed edges with cost 2*w
        for (auto &e : edges)
        {
            g[e[1]].push_back({e[0], 2 * e[2]});
        }

        // Dijkstra
        vector<long long> dist(n, INF);
        dist[0] = 0;
        using P = pair<long long, int>;
        priority_queue<P, vector<P>, greater<P>> pq;
        pq.push({0, 0});

        while (!pq.empty())
        {
            auto [d, u] = pq.top();
            pq.pop();
            if (d != dist[u])
                continue;
            if (u == n - 1)
                break;
            for (auto &ed : g[u])
            {
                int v = ed.first;
                long long w = ed.second;
                if (dist[v] > d + w)
                {
                    dist[v] = d + w;
                    pq.push({dist[v], v});
                }
            }
        }
        return dist[n - 1] >= INF ? -1 : (int)dist[n - 1];
    }
};
