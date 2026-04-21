import java.io.*;
import java.util.*;

public class knighthaunt {
    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st;

        int n = Integer.parseInt(data.readLine().trim());

        st = new StringTokenizer(data.readLine());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());

        int[] dx = {2,  2, -2, -2, 1,  1, -1, -1};
        int[] dy = {1, -1,  1, -1, 2, -2,  2, -2};

        int[][] dist = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dist[i][j] = -1;
            }
        }

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        dist[x1][y1] = 0;
        queue.add(x1 * (n + 1) + y1);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            int x = curr / (n + 1);
            int y = curr % (n + 1);

            if (x == x2 && y == y2) {
                break;
            }

            for (int d = 0; d < 8; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= 1 && nx <= n && ny >= 1 && ny <= n && dist[nx][ny] == -1) {
                    dist[nx][ny] = dist[x][y] + 1;
                    queue.add(nx * (n + 1) + ny);
                }
            }
        }

        out.println(dist[x2][y2]);

        out.flush();
        out.close();
    }
}
