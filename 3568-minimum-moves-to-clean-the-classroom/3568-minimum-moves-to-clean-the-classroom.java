class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int[][] id = new int[m][n];
        int sx = 0, sy = 0, count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);

                if (c == 'S') {
                    sx = i;
                    sy = j;
                } else if (c == 'L') {
                    id[i][j] = count++;
                }
            }
        }

        if (count == 0) {
            return 0;
        }

        int totalMasks = 1 << count;
        boolean[][][][] visited = new boolean[m][n][energy + 1][totalMasks];

        Queue<int[]> queue = new ArrayDeque<>();
        int initialMask = totalMasks - 1;

        queue.offer(new int[]{sx, sy, energy, initialMask});
        visited[sx][sy][energy][initialMask] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int[] state = queue.poll();

                int x = state[0];
                int y = state[1];
                int e = state[2];
                int mask = state[3];

                if (mask == 0) {
                    return moves;
                }

                if (e == 0) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || nx >= m || ny < 0 || ny >= n ||
                        classroom[nx].charAt(ny) == 'X') {
                        continue;
                    }

                    char cell = classroom[nx].charAt(ny);
                    int ne = e - 1;
                    int nm = mask;

                    if (cell == 'R') {
                        ne = energy;
                    }

                    if (cell == 'L') {
                        nm &= ~(1 << id[nx][ny]);
                    }

                    if (!visited[nx][ny][ne][nm]) {
                        visited[nx][ny][ne][nm] = true;
                        queue.offer(new int[]{nx, ny, ne, nm});
                    }
                }
            }

            moves++;
        }
        return -1;
    }
}