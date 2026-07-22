import java.util.*;

class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();

        int totalOnes = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') totalOnes++;
        }

        // Build run-length decomposition of the whole string
        int[] runStart = new int[n];
        int[] runEnd = new int[n];
        char[] runChar = new char[n];
        int[] runLen = new int[n];
        int numRuns = 0;
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) j++;
            runStart[numRuns] = i;
            runEnd[numRuns] = j - 1;
            runChar[numRuns] = s.charAt(i);
            runLen[numRuns] = j - i;
            numRuns++;
            i = j;
        }

        // V[j] = runLen[j-1] + runLen[j+1] if runChar[j]=='1' and j is strictly interior
        final long NEG = Long.MIN_VALUE / 2;
        long[] V = new long[numRuns];
        Arrays.fill(V, NEG);
        for (int j = 1; j < numRuns - 1; j++) {
            if (runChar[j] == '1') {
                V[j] = (long) runLen[j - 1] + runLen[j + 1];
            }
        }

        // Sparse table for range-max queries on V
        int K = 1;
        while ((1 << K) <= Math.max(numRuns, 1)) K++;
        long[][] sparse = new long[K][numRuns];
        if (numRuns > 0) {
            sparse[0] = V.clone();
            for (int k = 1; k < K; k++) {
                int half = 1 << (k - 1);
                int len = 1 << k;
                for (int idx = 0; idx + len - 1 < numRuns; idx++) {
                    sparse[k][idx] = Math.max(sparse[k - 1][idx], sparse[k - 1][idx + half]);
                }
            }
        }
        int[] log2 = new int[numRuns + 1];
        for (int x = 2; x <= numRuns; x++) log2[x] = log2[x / 2] + 1;

        int[] runStartArr = Arrays.copyOf(runStart, numRuns);

        int qn = queries.length;
        List<Integer> answer = new ArrayList<>(qn);

        for (int qi = 0; qi < qn; qi++) {
            int l = queries[qi][0];
            int r = queries[qi][1];

            int p = findRun(runStartArr, l, numRuns);
            int q = findRun(runStartArr, r, numRuns);

            long candidateMax = NEG;

            if (p == q) {
                // single run inside [l,r], no trade possible
            } else if (q == p + 1) {
                // only two runs, no interior 1-run possible
            } else {
                int k = q - p;
                long leftTrunc = (long) runEnd[p] - l + 1;
                long rightTrunc = (long) r - runStart[q] + 1;

                if (k == 2) {
                    int j = p + 1;
                    if (runChar[j] == '1') {
                        candidateMax = leftTrunc + rightTrunc;
                    }
                } else {
                    // k >= 3
                    int j1 = p + 1;
                    if (runChar[j1] == '1') {
                        long val = leftTrunc + runLen[p + 2];
                        candidateMax = Math.max(candidateMax, val);
                    }
                    int j2 = q - 1;
                    if (runChar[j2] == '1') {
                        long val = (long) runLen[q - 2] + rightTrunc;
                        candidateMax = Math.max(candidateMax, val);
                    }
                    if (k >= 4) {
                        int lo = p + 2, hi = q - 2;
                        if (lo <= hi) {
                            int len = hi - lo + 1;
                            int kk = log2[len];
                            long m = Math.max(sparse[kk][lo], sparse[kk][hi - (1 << kk) + 1]);
                            candidateMax = Math.max(candidateMax, m);
                        }
                    }
                }
            }

            long gain = Math.max(candidateMax, 0L);
            answer.add((int) (totalOnes + gain));
        }

        return answer;
    }

    private int findRun(int[] runStart, int x, int numRuns) {
        int lo = 0, hi = numRuns - 1, ans = 0;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (runStart[mid] <= x) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }
}