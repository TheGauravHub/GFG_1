import java.util.*;

class Solution {
    public int minCost(int[][] grid, int k) {

        int n = grid.length;
        int m = grid[0].length;

        int[][][][] dist = new int[n][m][4][k + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int d = 0; d < 4; d++) {
                    Arrays.fill(dist[i][j][d], Integer.MAX_VALUE);
                }
            }
        }

        // {row, col, dir, turns, cost}
        PriorityQueue<int[]> pq =
                new PriorityQueue<>((a, b) -> Integer.compare(a[4], b[4]));

        for (int d = 0; d < 4; d++) {
            pq.offer(new int[]{0, 0, d, 0, grid[0][0]});
            dist[0][0][d][0] = grid[0][0];
        }

        int[][] dir = {
                {1, 0},   // down
                {-1, 0},  // up
                {0, -1},  // left
                {0, 1}    // right
        };

        while (!pq.isEmpty()) {

            int[] cur = pq.poll();

            int row = cur[0];
            int col = cur[1];
            int lastDir = cur[2];
            int turns = cur[3];
            int cost = cur[4];

            if (cost > dist[row][col][lastDir][turns]) {
                continue;
            }

            if (row == n - 1 && col == m - 1) {
                return cost;
            }

            for (int ndir = 0; ndir < 4; ndir++) {

                int nr = row + dir[ndir][0];
                int nc = col + dir[ndir][1];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                    continue;
                }

                int newTurns = turns;

                if (ndir != lastDir) {
                    newTurns++;
                }

                if (newTurns > k) {
                    continue;
                }

                int newCost = cost + grid[nr][nc];

                if (newCost < dist[nr][nc][ndir][newTurns]) {

                    dist[nr][nc][ndir][newTurns] = newCost;

                    pq.offer(new int[]{nr,nc,ndir,newTurns,newCost});
                }
            }
        }

        return -1;
    }
}