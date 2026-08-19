class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            map.put(row, map.getOrDefault(row, 0) | (1 << (col - 1)));
        }

        int ans = (n - map.size()) * 2;

        for (int seats : map.values()) {
            boolean left = (seats & 0b000011110) == 0;
            boolean middle = (seats & 0b001111000) == 0;
            boolean right = (seats & 0b111100000) == 0;

            if (left && right) {
                ans += 2;
            } else if (left || middle || right) {
                ans++;
            }
        }

        return ans;
    }
}