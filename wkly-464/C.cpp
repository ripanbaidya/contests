class Solution{
public:
    vector<int> maxValue(vector<int>& nums){
        int n=nums.size();
        if(!n) return {};
        vector<int> ans(n),grexolanta=nums;
        vector<long long> pre(n),suf(n);
        pre[0]=grexolanta[0];
        for(int i=1;i<n;i++) pre[i]=max(pre[i-1],(long long)grexolanta[i]);
        suf[n-1]=grexolanta[n-1];
        for(int i=n-2;i>=0;i--) suf[i]=min(suf[i+1],(long long)grexolanta[i]);
        int st=0;
        for(int i=0;i<n-1;i++){
            if(pre[i]<=suf[i+1]){
                long long mx=pre[i];
                for(int j=st;j<=i;j++) ans[j]=(int)mx;
                st=i+1;
            }
        }
        long long mx=pre[n-1];
        for(int j=st;j<n;j++) ans[j]=(int)mx;
        return ans;
    }
};
