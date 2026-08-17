class Solution {
    int[] prefix;
    int[] stones;
    Integer[][] dp;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        stones = stoneValue;
        prefix = new int[n + 1];
        dp = new Integer[n][n];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        return dfs(0, n - 1);
    }

    private int dfs(int l, int r) {
        if (l >= r) return 0;

        if (dp[l][r] != null) {
            return dp[l][r];
        }

        int ans = 0;
        int leftSum = 0;
        int rightSum = prefix[r + 1] - prefix[l];

        for (int k = l; k < r; k++) {
            leftSum += stones[k];
            rightSum -= stones[k];

            if (leftSum < rightSum) {
                if (ans >= leftSum * 2) {
                    continue;
                }

                ans = Math.max(ans, leftSum + dfs(l, k));
            } else if (leftSum > rightSum) {
                if (ans >= rightSum * 2) {
                    break;
                }

                ans = Math.max(ans, rightSum + dfs(k + 1, r));
            } else {
                ans = Math.max(ans,
                    Math.max(
                        leftSum + dfs(l, k),
                        rightSum + dfs(k + 1, r)
                    )
                );
            }
        }

        return dp[l][r] = ans;
    }
}