class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        Integer[] idx = new Integer[n];

        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }

        Arrays.sort(idx, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] ans = new int[n];
        int start = 0;

        while (start < n) {
            int end = start;

            while (end + 1 < n && nums[idx[end + 1]] - nums[idx[end]] <= limit) {
                end++;
            }

            int[] positions = new int[end - start + 1];

            for (int i = start; i <= end; i++) {
                positions[i - start] = idx[i];
            }

            Arrays.sort(positions);

            for (int i = 0; i < positions.length; i++) {
                ans[positions[i]] = nums[idx[start + i]];
            }

            start = end + 1;
        }

        return ans;
    }
}