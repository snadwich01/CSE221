import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class bfs {
    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = null;

        String line = data.readLine();

        if (line == null) return;

        st = new StringTokenizer(line);
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] u = new int[m], v = new int[m];
        int[] deg = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(data.readLine());
            u[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());

            deg[u[i]]++;
            deg[v[i]]++;
        }

        int[][] adj = new int[n + 1][];
        for (int i = 1; i <= n; i++){
            adj[i] = new int[deg[i]];
        }

        int[] idx = new int[n + 1];
        for (int i = 0; i < m; i++) {
            adj[u[i]][idx[u[i]]++] = v[i];
            adj[v[i]][idx[v[i]]++] = u[i];
        }

        int[] col = new int[n + 1];
        int[] queue = new int[n + 1];
        int head = 0, tail = 0;

        col[1] = 1;
        queue[tail++] = 1;

        while (head < tail) {
            int node = queue[head++];
            for (int i = 0; i < deg[node]; i++) {
                if (col[adj[node][i]] == 0) {
                    col[adj[node][i]] = 1;
                    queue[tail++] = adj[node][i];
                }
            }
        }

        for (int i = 0; i < tail; i++) {
            if (i > 0) out.print(" ");
            out.print(queue[i]);
        }
        out.println();

        out.flush();
        data.close();
    }
}
