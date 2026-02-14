class Solution{
public:

    bool partitionArray(vector<int>& nums,int k){
        int n=nums.size();
        if(n%k) return 0;
        int g=n/k;
        vector<int> v=nums;
        unordered_map<int,int> mp;
        for(auto x:v) mp[x]++;
        int mx=0;
        for(auto &p:mp) mx=max(mx,p.second);
        return mx<=g;
    }
};
