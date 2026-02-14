class Solution {
public:

    int getLeastFrequentDigit(int n) {
        vector<int> f(10,0);
        while(n>0){
            f[n%10]++;
            n/=10;
        }
        int mn=1e9,ans=-1;
        for(int d=0;d<10;d++){
            if(f[d]){
                if(f[d]<mn || (f[d]==mn && d<ans)){
                    mn=f[d];
                    ans=d;
                }
            }
        }
        return ans;
    }
};
