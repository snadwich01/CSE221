import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class konigsegg {
    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = null;

        String line = data.readLine();

        if (line == null) return;

        st = new StringTokenizer(line);
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] x = new int[k], y = new int[k];
        boolean[][] board = new boolean[n + 1][m + 1];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(data.readLine());

            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());

            board[x[i]][y[i]] = true;
        }

        int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

        boolean found = false;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < 8; j++) {
                int nx = x[i] + dx[j];
                int ny = y[i] + dy[j];

                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m && board[nx][ny]) {
                    found = true;
                }
            }
        }

        if (found) {
            out.println("YES");
        } else {
            out.println("NO");
        }

        out.flush();
        data.close();
    }
}
