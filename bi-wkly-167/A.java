class Solution {
    public boolean scoreBalance(String s) {
        int n = s.length();
        int sum = 0;
        for(int i=0;i<n;i++) sum += s.charAt(i)-'a'+1;

        int pref = 0;
        for(int i=0;i<n-1;i++){
            pref += s.charAt(i)-'a'+1;
            if(pref == sum-pref) return true;
        }
        
        return false;    
    }
}