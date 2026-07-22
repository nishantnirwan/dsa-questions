class Solution {

    static boolean isValid(int[] nums, int k, int maxSum) {
        int subarrays = 1;
        int currentSum = 0;

        for(int num : nums) {
            if(currentSum + num <= maxSum) {
                currentSum += num;
            }
            else {
                subarrays++;
                currentSum = num;

                if(subarrays > k) {
                    return false;
                }
            }
        }
        return true;
    }

    public int splitArray(int[] nums, int k) {
        int n = nums.length;
        int s = 0;
        int e = 0;
        for(int x : nums) {
            s = Math.max(s, x);
            e += x;
        }

        int ans = -1;

        while(s <= e) {
            int mid = (s + (e - s) / 2);
            if(isValid(nums, k, mid)) {
                ans = mid;
                e = mid - 1;
            }
            else {
                s = mid + 1;
            }
        }
        return ans;
    }
}