import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class lightning {
    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st;

        st = new StringTokenizer(data.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[] src  = new int[m];
        int[] dest = new int[m];
        int[] deg  = new int[n + 1];

        if (m > 0) {
            st = new StringTokenizer(data.readLine());
            for (int i = 0; i < m; i++) src[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(data.readLine());
            for (int i = 0; i < m; i++) dest[i] = Integer.parseInt(st.nextToken());

            for (int i = 0; i < m; i++) {
                deg[src[i]]++;
                deg[dest[i]]++;
            }
        }

        int[][] adj = new int[n + 1][];
        for (int i = 1; i <= n; i++) adj[i] = new int[deg[i]];

        int[] pos = new int[n + 1];
        for (int i = 0; i < m; i++) {
            adj[src[i]][pos[src[i]]++] = dest[i];
            adj[dest[i]][pos[dest[i]]++] = src[i];
        }

        if (s == d) {
            out.println(0);
            out.println(s);
            out.flush();
            data.close();
            return;
        }

        int[] dist = new int[n + 1];
        for (int i = 0; i <= n; i++) dist[i] = -1;

        int[] queue = new int[n + 1];
        int head = 0, tail = 0;

        dist[d] = 0;
        queue[tail++] = d;

        while (head < tail) {
            int node = queue[head++];
            for (int i = 0; i < deg[node]; i++) {
                int nb = adj[node][i];
                if (dist[nb] == -1) {
                    dist[nb] = dist[node] + 1;
                    queue[tail++] = nb;
                }
            }
        }
        if (dist[s] == -1) {
            out.println(-1);
            out.flush();
            data.close();
            return;
        }

        int shortest = dist[s];
        int[] path = new int[shortest + 1];
        path[0] = s;

        int cur = s;
        for (int step = 1; step <= shortest; step++) {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < deg[cur]; i++) {
                int nb = adj[cur][i];
                if (dist[nb] == dist[cur] - 1 && nb < best) {
                    best = nb;
                }
            }
            path[step] = best;
            cur = best;
        }

        out.println(shortest);
        for (int i = 0; i <= shortest; i++) {
            if (i > 0) out.print(" ");
            out.print(path[i]);
        }
        out.println();

        out.flush();
        data.close();
    }
}
