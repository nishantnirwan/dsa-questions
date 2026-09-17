class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int l = 0;
        int r = n-1;

        int[] ans = new int[n];

        for(int i = n-1; i >= 0; i--) {
            int val = 0;

            if(Math.abs(nums[l]) > Math.abs(nums[r])) {
                val = nums[l];
                l++;
            }
            else {
                val = nums[r];
                r--;
            }
            ans[i] = val * val;
        }
        return ans;
    }
}