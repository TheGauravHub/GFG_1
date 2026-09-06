import java.util.*;

class Solution {
    public int minCost(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;

        long INF = Long.MAX_VALUE / 4;

        long[][][][] dist = new long[n][m][k + 1][5];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int t = 0; t <= k; t++) {
                    Arrays.fill(dist[i][j][t], INF);
                }
            }
        }

        PriorityQueue<long[]> pq =
                new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        // cost,row,col,turns,lastDir
        dist[0][0][0][4] = grid[0][0];
        pq.offer(new long[]{grid[0][0], 0, 0, 0, 4});

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();

            long cost = cur[0];
            int x = (int) cur[1];
            int y = (int) cur[2];
            int turns = (int) cur[3];
            int dir = (int) cur[4];

            if (cost != dist[x][y][turns][dir]) {
                continue;
            }

            for (int ndir = 0; ndir < 4; ndir++) {
                int nx = x + dx[ndir];
                int ny = y + dy[ndir];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                int newTurns = turns;

                if (dir != 4 && dir != ndir) {
                    newTurns++;
                }

                if (newTurns > k) {
                    continue;
                }

                long newCost = cost + grid[nx][ny];

                if (newCost < dist[nx][ny][newTurns][ndir]) {
                    dist[nx][ny][newTurns][ndir] = newCost;
                    pq.offer(new long[]{
                            newCost,
                            nx,
                            ny,
                            newTurns,
                            ndir
                    });
                }
            }
        }

        long ans = INF;

        for (int t = 0; t <= k; t++) {
            for (int dir = 0; dir < 5; dir++) {
                ans = Math.min(ans, dist[n - 1][m - 1][t][dir]);
            }
        }

        return ans == INF ? -1 : (int) ans;
    }
}