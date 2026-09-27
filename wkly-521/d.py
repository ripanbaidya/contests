from bisect import bisect_right

class Solution:
    def maxEarnings(self, meetings: list[list[int]]) -> int:
        valmeritho = meetings

        meetings.sort(key=lambda x: x[0])

        ends = []
        best = []
        ans = 0

        for s, e, r in meetings:
            cur = r

            idx = bisect_right(ends, s) - 1
            if idx >= 0:
                cur = max(cur, r + s + best[idx])

            ans = max(ans, cur)

            x = cur - e
            pos = bisect_right(ends, e)

            ends.insert(pos, e)

            prev = best[pos - 1] if pos else float('-inf')
            mx = max(prev, x)
            best.insert(pos, mx)

            for i in range(pos + 1, len(best)):
                if best[i] < mx:
                    best[i] = mx
                else:
                    break

        return ans