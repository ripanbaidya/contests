#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<int> minDifference(int n, int k) {
        vector<int> divs;
        for(int d=1;1LL*d*d<=n;d++){
            if(n%d==0){
                divs.push_back(d);
                if(d*d!=n) divs.push_back(n/d);
            }
        }
        sort(divs.begin(),divs.end());
        
        vector<int> cur, best;
        long long bestDiff=LLONG_MAX;
        
        function<void(int,int,int)> dfs=[&](int need,int rem,int idx){
            if(need==1){
                if(rem>=divs[idx]){
                    cur.push_back(rem);
                    int mn=cur[0],mx=cur[0];
                    for(int x:cur){mn=min(mn,x);mx=max(mx,x);}
                    long long diff=1LL*mx-mn;
                    if(diff<bestDiff){
                        bestDiff=diff;
                        best=cur;
                    }
                    cur.pop_back();
                }
                return;
            }
            for(int i=idx;i<(int)divs.size();i++){
                int d=divs[i];
                if(d>rem) break;
                if(rem%d) continue;
                cur.push_back(d);
                dfs(need-1,rem/d,i);
                cur.pop_back();
            }
        };
        
        dfs(k,n,0);
        if(best.empty()) return {n};
        return best;
    }
};
©leetcode