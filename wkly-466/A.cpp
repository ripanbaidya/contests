class Solution {
public:
    int minOperations(vector<int>& a) {
        for(int i=1;i<a.size();i++) if(a[i]!=a[0]) return 1;
        return 0;
    }
};
