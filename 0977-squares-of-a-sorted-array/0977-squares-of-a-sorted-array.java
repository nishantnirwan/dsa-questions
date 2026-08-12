class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int l = 0;
        int r = n - 1;
        int[] ans = new int[n];
        
        for(int i = n - 1; i >= 0; i--) {
            int value = 0;
            if(Math.abs(nums[l]) > Math.abs(nums[r])) {
                value = nums[l];
                l++;
            }
            else {
                value = nums[r];
                r--;
            }
            ans[i] = value * value;
        }
        return ans;
    }
}