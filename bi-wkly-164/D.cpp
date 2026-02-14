class Solution {
public:

    int minOperations(string s,int k){
        int n=s.size(),z=0;
        for(char c:s) if(c=='0') z++;
        if(!z) return 0;

        if(k==n){
            if(!z) return 0;
            if(z==n) return 1;
            return -1;
        }

        int st=(z+k-1)/k;
        for(int m=max(1,st);m<=n;m++){
            long long tot=1LL*m*k;
            if((tot&1)!=(z&1)) continue;
            long long Smin=z;
            int r=(m%2==0?z:n-z);
            long long Smax=1LL*n*m-r;
            if(Smin<=tot && tot<=Smax) return m;
        }
        return -1;
    }
};
