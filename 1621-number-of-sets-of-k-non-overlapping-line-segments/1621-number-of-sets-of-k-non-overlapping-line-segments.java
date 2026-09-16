class Solution {
    public int numberOfSets(int n, int k) {
        int N = n + k - 1;
        int K = 2 * k;
        long MOD = 1000000007;

        long[] dp = new long[K + 1];
        dp[0] = 1;

        for (int i = 1; i <= N; i++) {
            for (int j = Math.min(i, K); j >= 1; j--) {
                dp[j] = (dp[j] + dp[j - 1]) % MOD;
            }
        }

        return (int) dp[K];
    }
}