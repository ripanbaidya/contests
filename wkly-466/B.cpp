class Solution {
public:
    int minOperations(string s) {
        vector<int> seen(26,0);
        for(char c:s) seen[c-'a']=1;

        int ans=0;
        for(int i=1;i<26;i++){
            if(seen[i]){
                ans++;
                seen[(i+1)%26]=1;
            }
        }
        return ans;
    }
};
