class Solution {

    static int pivotIndex(int[] arr) {
        int n = arr.length;
        int s = 0;
        int e = n-1;
        int ans = -1;

        while(s <= e) {
            int mid = s + (e-s) / 2;
            if(arr[mid] <= arr[n-1]) {
                e = mid - 1;
            }
            else {
                ans = mid;
                s = mid + 1;
            }
        }
        return ans;
    }


    static int search(int[] nums, int s, int e, int target) {
        while(s <= e) {
            int mid = s + (e-s) / 2;
            if(nums[mid] == target) {
                return mid;
            }
            else if(nums[mid] > target) {
                e = mid - 1;
            }
            else {
                s = mid + 1;
            }
        }
        return -1;
    }


    public int search(int[] nums, int target) {
        int n = nums.length;
        int pivot = pivotIndex(nums);

        if(pivot == -1) {
            int ans = search(nums, 0, n-1, target);
            return ans;
        }

        else {
            int startArray1 = 0;
            int endArray1 = pivot;

            if(target >= nums[startArray1] && target <= nums[endArray1]) {
                int ans = search(nums, startArray1, endArray1, target);
                return ans;
            }

            int startArray2 = pivot + 1;
            int endArray2 = n - 1;

            if(target >= nums[startArray2] && target <= nums[endArray2]) {
                int ans = search(nums, startArray2, endArray2, target);
                return ans;
            }
        }

        return -1;
    }
}