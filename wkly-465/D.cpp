#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int totalBeauty(vector<int>& nums) {
        const int MOD=1000000007;
        int n=nums.size();
        if(!n) return 0;

        int mx=0;
        for(int v:nums) mx=max(mx,v);

        vector<vector<int>> pos(mx+1);
        for(int i=0;i<n;i++){
            int v=nums[i], r=sqrt(v);
            for(int d=1;d<=r;d++){
                if(v%d==0){
                    pos[d].push_back(i);
                    int o=v/d;
                    if(o!=d) pos[o].push_back(i);
                }
            }
        }

        vector<int> bit(mx+1);
        auto add=[&](int idx,int val){
            while(idx<=mx){
                long long nv=bit[idx]+1LL*val;
                if(nv>=MOD) nv-=MOD;
                bit[idx]=nv;
                idx+=idx&-idx;
            }
        };
        auto sum=[&](int idx){
            long long res=0;
            while(idx>0){
                res+=bit[idx];
                if(res>=(1LL<<62)) res%=MOD;
                idx-=idx&-idx;
            }
            return (int)(res%MOD);
        };

        vector<int> F(mx+1);
        for(int d=1;d<=mx;d++){
            if(pos[d].empty()) continue;
            vector<pair<int,int>> upd;
            upd.reserve(pos[d].size());
            for(int idx:pos[d]){
                int v=nums[idx];
                int s=(v>1?sum(v-1):0);
                int cnt=s+1;
                if(cnt>=MOD) cnt-=MOD;
                F[d]+=cnt;
                if(F[d]>=MOD) F[d]-=MOD;
                add(v,cnt);
                upd.emplace_back(v,cnt);
            }
            for(auto &p:upd){
                int neg=(MOD-p.second)%MOD;
                add(p.first,neg);
            }
        }

        vector<int> exact(mx+1);
        for(int d=mx;d>=1;d--){
            long long cur=F[d];
            for(int m=d+d;m<=mx;m+=d){
                cur-=exact[m];
                if(cur<0) cur+=MOD;
            }
            exact[d]=cur%MOD;
        }

        long long ans=0;
        for(int d=1;d<=mx;d++){
            if(exact[d]){
                ans+=(1LL*d%MOD)*exact[d];
                ans%=MOD;
            }
        }
        return (int)ans;
    }
};
