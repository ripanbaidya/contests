#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<int> recoverOrder(vector<int>& order, vector<int>& friends) {
        unordered_set<int> st(friends.begin(), friends.end());
        vector<int> ans;
        ans.reserve(friends.size());
        for(auto x: order){
            if(st.count(x)) ans.push_back(x);
            if(ans.size()==friends.size()) break;
        }
        return ans;
    }
};
