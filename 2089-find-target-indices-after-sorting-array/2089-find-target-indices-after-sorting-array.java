class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int smaller = 0;
        int count = 0;

        for (int num : nums) {
            if (num < target) {
                smaller++;
            } else if (num == target) {
                count++;
            }
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            ans.add(smaller + i);
        }
        return ans;
    }
}