class Solution {

    public long countNoZeroPairs(long n) {
        String s = Long.toString(n);
        int L = s.length();
        int[] nd = new int[L];
        for(int i=0;i<L;i++) nd[L-1-i]=s.charAt(i)-'0';
        
        long res = 0;
        
        for(int la=1;la<=L;la++){
            for(int lb=1;lb<=L;lb++){
                long[] dp = new long[2];
                dp[0]=1;
                for(int pos=0;pos<L;pos++){
                    long[] ndp = new long[2];
                    int need = nd[pos];
                    int aLo=(pos<la)?1:0, aHi=(pos<la)?9:0;
                    int bLo=(pos<lb)?1:0, bHi=(pos<lb)?9:0;
                    for(int carry=0;carry<=1;carry++){
                        long ways=dp[carry];
                        if(ways==0) continue;
                        for(int da=aLo;da<=aHi;da++){
                            for(int db=bLo;db<=bHi;db++){
                                int sum=da+db+carry;
                                if(sum%10==need){
                                    int nc=sum/10;
                                    ndp[nc]+=ways;
                                }
                            }
                        }
                    }
                    dp=ndp;
                }
                res+=dp[0];
            }
        }

        return res;
    }
}
