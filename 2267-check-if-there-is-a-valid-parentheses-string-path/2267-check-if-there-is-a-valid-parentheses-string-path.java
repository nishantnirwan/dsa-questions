class Solution {
    int m, n;
    char[][] grid;
    boolean[][][] vis;

    public boolean hasValidPath(char[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        if ((m + n - 1) % 2 == 1 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        vis = new boolean[m][n][m + n];

        return dfs(0, 0, 0);
    }

    private boolean dfs(int r, int c, int balance) {
        if (vis[r][c][balance]) {
            return false;
        }

        vis[r][c][balance] = true;

        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }

        if (balance < 0) {
            return false;
        }

        int remaining = (m - 1 - r) + (n - 1 - c);

        if (balance > remaining) {
            return false;
        }

        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        if (r + 1 < m && dfs(r + 1, c, balance)) {
            return true;
        }

        if (c + 1 < n && dfs(r, c + 1, balance)) {
            return true;
        }

        return false;
    }
}