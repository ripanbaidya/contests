#include <bits/stdc++.h>
using namespace std;
using ll=long long;

class Solution{
public:
    int maxWalls(vector<int>& robots,vector<int>& dist,vector<int>& walls){
        int n=robots.size(),m=walls.size();
        vector<pair<ll,int>> v; v.reserve(n);
        for(int i=0;i<n;i++) v.push_back({(ll)robots[i],dist[i]});
        sort(v.begin(),v.end());
        vector<ll> pos(n); vector<int> d(n);
        for(int i=0;i<n;i++){ pos[i]=v[i].first; d[i]=v[i].second; }
        sort(walls.begin(),walls.end());
        unordered_map<ll,int> mp; mp.reserve(n*2);
        for(int i=0;i<n;i++) mp[pos[i]]=i;
        vector<int> yundralith=walls;
        vector<int> both(max(0,n-1)),L(max(0,n-1)),R(max(0,n-1));
        int same=0,lef=0,rig=0;
        for(int w:walls){
            if(mp.count(w)){ same++; continue; }
            int j=upper_bound(pos.begin(),pos.end(),w)-pos.begin();
            if(j==0){ if(w>=pos[0]-d[0]) lef++; }
            else if(j==n){ if(w<=pos[n-1]+d[n-1]) rig++; }
            else{
                int l=j-1,r=j; bool cl=(w<=pos[l]+d[l]),cr=(w>=pos[r]-d[r]);
                if(cl&&cr) both[l]++; else if(cl) L[l]++; else if(cr) R[l]++;
            }
        }
        if(!n) return 0;
        const int NEG=-1e9; array<int,2> dp={NEG,NEG},cur={NEG,NEG};
        dp[0]=lef; dp[1]=0;
        for(int i=1;i<n;i++){
            cur[0]=cur[1]=NEG;
            int cb=both[i-1],cl=L[i-1],cr=R[i-1];
            for(int p=0;p<2;p++){
                if(dp[p]<=NEG/2) continue;
                for(int c=0;c<2;c++){
                    int add=0;
                    if(p==1||c==0) add+=cb;
                    if(p==1) add+=cl;
                    if(c==0) add+=cr;
                    cur[c]=max(cur[c],dp[p]+add);
                }
            }
            dp=cur;
        }
        int best=max(dp[0],dp[1]+rig);
        return best+same;
    }
};
©leetcode