class Solution
{
public:
    vector<vector<int>> reverseSubmatrix(vector<vector<int>> &g, int r, int c, int k)
    {
        int t = r, b = r + k - 1;
        while (t < b)
        {
            for (int j = 0; j < k; j++)
            {
                swap(g[t][c + j], g[b][c + j]);
            }
            t++;
            b--;
        }
        return g;
    }
};
