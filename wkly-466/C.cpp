class Solution {
public:
    long long bowlSubarrays(vector<int>& a) {
        int n = a.size();
        vector<int> nxt(n,-1), prv(n,-1);

        {   // next greater
            vector<int> st;
            for (int i=n-1;i>=0;i--){
                while(!st.empty() && a[st.back()]<=a[i]) st.pop_back();
                if(!st.empty()) nxt[i]=st.back();
                st.push_back(i);
            }
        }
        {   // prev greater
            vector<int> st;
            for (int i=0;i<n;i++){
                while(!st.empty() && a[st.back()]<=a[i]) st.pop_back();
                if(!st.empty()) prv[i]=st.back();
                st.push_back(i);
            }
        }

        long long ans=0;
        for(int i=0;i<n;i++){
            if(nxt[i]!=-1 && nxt[i]-i>=2) ans++;
            if(prv[i]!=-1 && i-prv[i]>=2) ans++;
        }
        return ans;
    }
};
