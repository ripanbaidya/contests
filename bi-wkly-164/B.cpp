class Solution {
public:
    int score(vector<string>& a,char x){
        int n=a.size();
        int xx=0;
        const int A=10;
        vector<int> c1(A,0),c2(A,0);
        for(auto &s:a){
            bool l=(s[0]==x),r=(s[1]==x);
            if(!l && !r) continue;
            if(l&&r) xx++;
            else if(l) c1[s[1]-'a']++;
            else c2[s[0]-'a']++;
        }
        int S1=0,m1=0; for(int v:c1){S1+=v; m1=max(m1,v);}
        int S2=0,m2=0; for(int v:c2){S2+=v; m2=max(m2,v);}
        int p1=(S1?min(S1/2,S1-m1):0);
        int p2=(S2?min(S2/2,S2-m2):0);
        int P=p1+p2, S=S1+S2;
        if(S+xx==0) return 0;
        long double pr=(long double)(S-xx)/2.0;
        vector<int> cand;
        auto add=[&](long long v){ 
            if(v<0) v=0; if(v>P) v=P;
            cand.push_back((int)v);
        };
        add(0); add(P); add((long long)floor(pr)); add((long long)ceil(pr));
        sort(cand.begin(),cand.end());
        cand.erase(unique(cand.begin(),cand.end()),cand.end());
        long long best=0;
        for(int p:cand){
            long long left=S-2LL*p;
            long long take=min<long long>(xx,max(0LL,left));
            best=max(best, p+take);
        }
        return (int)best;
    }
};
©leetcode