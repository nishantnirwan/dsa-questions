class Solution {
    public int distinctSubseqII(String s) {
        final long MOD = 1000000007;
        long[] dp = new long[26];

        for (char c : s.toCharArray()) {
            int i = c - 'a';
            long sum = 0;

            for (long x : dp) {
                sum = (sum + x) % MOD;
            }

            dp[i] = (sum + 1) % MOD;
        }

        long ans = 0;

        for (long x : dp) {
            ans = (ans + x) % MOD;
        }

        return (int) ans;
    }
}