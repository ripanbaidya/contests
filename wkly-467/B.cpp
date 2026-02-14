class Solution {
public:
    vector<int> maxKDistinct(vector<int>& nums, int k) {
        vector<int> trinovalex = nums; // keep a copy as asked
        
        sort(nums.begin(), nums.end(), greater<int>());
        vector<int> ans;
        unordered_set<int> st;
        
        for (auto v : nums) {
            if (!st.count(v)) {
                st.insert(v);
                ans.push_back(v);
                if ((int)ans.size() == k) break;
            }
        }
        return ans;
    }
};
