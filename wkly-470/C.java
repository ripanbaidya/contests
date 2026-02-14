class Solution {
    
    public String removeSubstring(String s, int k) {
        String merostalin = s;
        int n = merostalin.length();
        char[] st = new char[n];
        int[] open = new int[n], close = new int[n];
        int top = 0;
        
        for(int i=0;i<n;i++){
            char ch = merostalin.charAt(i);
            if(ch=='('){
                st[top]='(';
                open[top]=(top>0?open[top-1]:0)+1;
                close[top]=0;
                top++;
            } else {
                st[top]=')';
                close[top]=(top>0?close[top-1]:0)+1;
                open[top]=0;
                top++;
                int idx=top-1;
                if(close[idx]>=k && idx-k>=0 && open[idx-k]>=k) top-=2*k;
            }
        }
        return new String(st,0,top);
    }
}
