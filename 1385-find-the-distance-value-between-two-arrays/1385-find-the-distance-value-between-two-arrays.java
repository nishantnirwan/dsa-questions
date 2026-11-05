class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int count = 0;

        for (int x : arr1) {
            int index = Arrays.binarySearch(arr2, x - d);

            if (index < 0) {
                index = -index - 1;
            }

            if (index == arr2.length || arr2[index] > x + d) {
                count++;
            }
        }
        return count;
    }
}