class Solution {

    class MedianWindow {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();

        int leftSize = 0, rightSize = 0;
        long leftSum = 0, rightSum = 0;

        void addMap(TreeMap<Integer, Integer> map, int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        void removeMap(TreeMap<Integer, Integer> map, int val) {
            int count = map.get(val);
            if (count == 1) map.remove(val);
            else map.put(val, count - 1);
        }

        void add(int val) {
            if (left.isEmpty() || val <= left.lastKey()) {
                addMap(left, val);
                leftSize++;
                leftSum += val;
            } else {
                addMap(right, val);
                rightSize++;
                rightSum += val;
            }
            balance();
        }

        void remove(int val) {
            if (left.containsKey(val)) {
                removeMap(left, val);
                leftSize--;
                leftSum -= val;
            } else {
                removeMap(right, val);
                rightSize--;
                rightSum -= val;
            }
            balance();
        }

        void balance() {
            while (leftSize > rightSize + 1) {
                int val = left.lastKey();
                removeMap(left, val);
                leftSize--;
                leftSum -= val;

                addMap(right, val);
                rightSize++;
                rightSum += val;
            }

            while (leftSize < rightSize) {
                int val = right.firstKey();
                removeMap(right, val);
                rightSize--;
                rightSum -= val;

                addMap(left, val);
                leftSize++;
                leftSum += val;
            }
        }

        long cost() {
            long median = left.lastKey();

            return median * leftSize - leftSum
                 + rightSum - median * rightSize;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        int[] maritovexi = nums;

        MedianWindow window = new MedianWindow();

        long[] cost = new long[n - x + 1];

        for (int i = 0; i < x; i++) {
            window.add(nums[i]);
        }

        cost[0] = window.cost();

        for (int i = x; i < n; i++) {
            window.remove(nums[i - x]);
            window.add(nums[i]);
            cost[i - x + 1] = window.cost();
        }

        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[k + 1][n + 1];

        for (long[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                if (dp[j][i] == INF) continue;

                dp[j][i + 1] = Math.min(
                    dp[j][i + 1],
                    dp[j][i]
                );

                if (j < k && i + x <= n) {
                    dp[j + 1][i + x] = Math.min(
                        dp[j + 1][i + x],
                        dp[j][i] + cost[i]
                    );
                }
            }
        }
        return dp[k][n];
    }
}