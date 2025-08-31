#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    long long maxProduct(vector<int>& nums) {
        int n=nums.size();
        int mx=0;
        for(int x:nums) mx=max(mx,x);

        int B=0;
        while((1<<B)<=mx) B++;
        if(B==0) B=1;
        int MS=1<<B, FULL=MS-1;

        vector<int> b1v(MS,-1), b1i(MS,-1), b2v(MS,-1), b2i(MS,-1);

        for(int i=0;i<n;i++){
            int v=nums[i], m=v&FULL;
            if(v>b1v[m]){
                if(b1i[m]!=i){ b2v[m]=b1v[m]; b2i[m]=b1i[m]; }
                b1v[m]=v; b1i[m]=i;
            }else if(v>b2v[m] && i!=b1i[m]){
                b2v[m]=v; b2i[m]=i;
            }
        }

        auto add=[&](int m,int val,int idx){
            if(val==-1) return;
            if(idx==b1i[m]){
                if(val>b1v[m]) b1v[m]=val;
                return;
            }
            if(val>b1v[m]){
                b2v[m]=b1v[m]; b2i[m]=b1i[m];
                b1v[m]=val; b1i[m]=idx;
            }else if(idx!=b1i[m] && val>b2v[m]){
                b2v[m]=val; b2i[m]=idx;
            }
        };

        for(int bit=0;bit<B;bit++){
            int bm=1<<bit;
            for(int m=0;m<MS;m++){
                if(m&bm){
                    int o=m^bm;
                    add(m,b1v[o],b1i[o]);
                    add(m,b2v[o],b2i[o]);
                }
            }
        }

        long long ans=0;
        for(int i=0;i<n;i++){
            int v=nums[i], m=v&FULL, comp=FULL^m;
            if(b1v[comp]!=-1 && b1i[comp]!=i) ans=max(ans,1LL*v*b1v[comp]);
            else if(b2v[comp]!=-1 && b2i[comp]!=i) ans=max(ans,1LL*v*b2v[comp]);
        }
        return ans;
    }
};
