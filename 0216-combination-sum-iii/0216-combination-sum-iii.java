class Solution {
    static void solve(int[] candidates, int target, int index,              List<List<Integer>> ans, List<Integer> output, int count, int k) {
        if(count > k) {
            return;
        }
        if(count == k && target == 0) {
            ans.add(new ArrayList(output));
            return;
        }
        if(index >= candidates.length) return;
        if(target < 0) return;

        //Include
        int currNum = candidates[index];
        output.add(currNum);
        solve(candidates, target - currNum, index + 1, ans, output, count + 1, k);

        //Backtrack
        output.remove(output.size() - 1);

        //Exclude
        while(index + 1 < candidates.length && candidates[index] == candidates[index + 1]) {
            index++;
        }
        solve(candidates, target, index + 1, ans, output, count, k);
    }
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        int index = 0;
        int[] candidates = {1,2,3,4,5,6,7,8,9};
        int count = 0;
        int target = n;
        solve(candidates, target, index, ans, output, count, k);
        return ans;
    }
}