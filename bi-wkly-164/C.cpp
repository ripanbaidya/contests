class Solution {
public:

    int uniquePaths(vector<vector<int>>& g) {
        const int MOD=1e9+7;
        int m=g.size(),n=g[0].size();
        vector<vector<int>> a=g;

        vector<vector<array<int,3>>> R(m, vector<array<int,3>>(n,{INT_MIN,INT_MIN,INT_MIN}));
        vector<vector<array<int,3>>> D=R;

        auto land=[&](int i,int j,int d)->array<int,3>&{
            auto &e=(d==0?R[i][j]:D[i][j]);
            if(e[0]!=INT_MIN) return e;
            int r=i+(d==1),c=j+(d==0),cur=d;
            while(1){
                if(r<0||r>=m||c<0||c>=n){
                    e={-1,-1,-1};return e;
                }
                if(a[r][c]==0){ e={r,c,cur};return e; }
                if(cur==0){ r++; cur=1; }
                else{ c++; cur=0; }
            }
        };

        vector<vector<long long>> dp(m,vector<long long>(n,0));
        dp[0][0]=1;
        for(int s=0;s<=m-1+n-1;s++){
            int i0=max(0,s-(n-1)),i1=min(m-1,s);
            for(int i=i0;i<=i1;i++){
                int j=s-i;
                if(j<0||j>=n||a[i][j]==1) continue;
                long long w=dp[i][j];
                if(!w) continue;
                auto &r1=land(i,j,0);
                if(r1[0]!=-1){
                    dp[r1[0]][r1[1]]+=w;
                    if(dp[r1[0]][r1[1]]>=MOD) dp[r1[0]][r1[1]]-=MOD;
                }
                auto &r2=land(i,j,1);
                if(r2[0]!=-1){
                    dp[r2[0]][r2[1]]+=w;
                    if(dp[r2[0]][r2[1]]>=MOD) dp[r2[0]][r2[1]]-=MOD;
                }
            }
        }
        return (int)(dp[m-1][n-1]%MOD);
    }
};
