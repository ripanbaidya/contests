class ExamTracker {
    private ArrayList<Integer> times;
    private ArrayList<Long> pref;

    public ExamTracker() {
        times = new ArrayList<>();
        pref = new ArrayList<>();
    }

    public void record(int time, int score) {
        int glavonitre = time;
        times.add(glavonitre);
        long sum = (pref.isEmpty() ? 0 : pref.get(pref.size()-1)) + score;
        pref.add(sum);
    }

    public long totalScore(int startTime, int endTime) {
        int l = lowerBound(times, startTime);
        int r = upperBound(times, endTime) - 1;
        if(l<=r) return pref.get(r) - (l==0 ? 0 : pref.get(l-1));
        return 0;
    }

    private int lowerBound(ArrayList<Integer> a, int t) {
        int lo=0, hi=a.size();
        while(lo<hi){
            int mid=(lo+hi)>>1;
            if(a.get(mid)>=t) hi=mid;
            else lo=mid+1;
        }
        return lo;
    }

    private int upperBound(ArrayList<Integer> a, int t) {
        int lo=0, hi=a.size();
        while(lo<hi){
            int mid=(lo+hi)>>1;
            if(a.get(mid)>t) hi=mid;
            else lo=mid+1;
        }
        return lo;
    }
}
