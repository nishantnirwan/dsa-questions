class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[] stack = new int[m * n];
        int ans = 0;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }

                int top = 0;
                long sum = grid[i][j];

                grid[i][j] = 0;
                stack[top++] = i * n + j;

                while (top > 0) {
                    int pos = stack[--top];
                    int r = pos / n;
                    int c = pos % n;

                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (nr >= 0 && nr < m &&
                            nc >= 0 && nc < n &&
                            grid[nr][nc] > 0) {

                            sum += grid[nr][nc];
                            grid[nr][nc] = 0;
                            stack[top++] = nr * n + nc;
                        }
                    }
                }

                if (sum % k == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }
}