class Solution {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        int s = 0;
        int e = n - 1;
        
        while(s <= e) {
            int mid = s + (e - s)/2;

            if(s == e) return nums[s];

            int currValue = nums[mid];
            int prevValue = -1;
            if(mid-1 >= 0) {
                prevValue = nums[mid-1];
            }
            int nextValue = -1;
            if(mid + 1 < n) {
                nextValue = nums[mid + 1];
            }

            if(currValue != prevValue && currValue != nextValue) {
                return currValue;
            }

            if(currValue != prevValue && currValue == nextValue) {
                int startingIndexOfPair = mid;
                if((startingIndexOfPair & 1) == 1) {
                    //startingIndex -> Odd
                    e = mid - 1;
                }
                else {
                    s = mid + 1;
                }
            }

            else if(currValue == prevValue && currValue != nextValue) {
                int endingIndexOfPair = mid;
                if((endingIndexOfPair & 1) == 1) {
                    s = mid + 1;
                }
                else {
                    e = mid - 1;
                }
            }
        }
        return -1;
    }
}