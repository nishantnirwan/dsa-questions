class Solution {
    public int minimizeTheDifference(int[][] mat, int target) {
        boolean[] dp = new boolean[4901];
        dp[0] = true;

        for (int[] row : mat) {
            boolean[] next = new boolean[4901];

            for (int sum = 0; sum <= 4900; sum++) {
                if (!dp[sum]) continue;

                for (int num : row) {
                    next[sum + num] = true;
                }
            }

            dp = next;
        }

        int ans = Integer.MAX_VALUE;

        for (int sum = 0; sum <= 4900; sum++) {
            if (dp[sum]) {
                ans = Math.min(ans, Math.abs(sum - target));
            }
        }

        return ans;
    }
}