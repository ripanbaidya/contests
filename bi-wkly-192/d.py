class Solution:
    def longestSubarray(self, nums: list[int], k: int) -> int:
        n = len(nums)

        pref = [0] * (n + 1)
        for i in range(n):
            pref[i + 1] = (pref[i] + nums[i]) % k

        ans = 0

        first = {}
        for i in range(n + 1):
            r = pref[i]
            if r in first:
                ans = max(ans, i - first[r])
            else:
                first[r] = i

        last = {}
        for i in range(n, -1, -1):
            r = pref[i]
            if r not in last:
                last[r] = i

        groups = {}
        for i in range(n):
            v = (2 * nums[i]) % k
            if v not in groups:
                groups[v] = []
            groups[v].append(i)

        import bisect

        for v, pos in groups.items():
            for r in range(k):
                if r not in first:
                    continue

                l = first[r]
                need = (r + v) % k

                if need not in last:
                    continue

                rr = last[need]

                idx = bisect.bisect_left(pos, l)
                if idx < len(pos) and pos[idx] < rr:
                    ans = max(ans, rr - l)

        return ans