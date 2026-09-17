class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] best = new int[n];
        int ans = Integer.MAX_VALUE;
        int left = 0, sum = 0;
        int bestLen = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target) {
                sum -= arr[left++];
            }

            if (sum == target) {
                int len = right - left + 1;

                if (left > 0 && best[left - 1] != 0) {
                    ans = Math.min(ans, len + best[left - 1]);
                }

                bestLen = Math.min(bestLen, len);
            }

            best[right] = bestLen == Integer.MAX_VALUE ? 0 : bestLen;
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}