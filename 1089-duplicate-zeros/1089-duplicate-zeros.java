class Solution {
    public void duplicateZeros(int[] arr) {
        int possibleZeroDups = 0;
        int lastIndx = arr.length - 1;

        for(int i = 0; i <= lastIndx - possibleZeroDups; i++) {
            if(arr[i] == 0) {
                if(i == lastIndx - possibleZeroDups) {
                    arr[lastIndx] = 0;
                    lastIndx--;
                    break;
                }
                possibleZeroDups++;
            }
        }
        int newLastIndx = lastIndx - possibleZeroDups;

        for(int i = newLastIndx; i >= 0; i--) {
            if(arr[i] == 0) {
                arr[i + possibleZeroDups] = 0;
                possibleZeroDups--;
                arr[i + possibleZeroDups] = 0;
            }
            else {
                arr[i + possibleZeroDups] = arr[i];
            }
        }
    }
}