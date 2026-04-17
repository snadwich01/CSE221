import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class detectotron {
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
        }

        int[][] adj = new int[n + 1][];
        for (int i = 1; i <= n; i++) adj[i] = new int[deg[i]];

        int[] pos = new int[n + 1];
        for (int i = 0; i < m; i++) {
            adj[u[i]][pos[u[i]]++] = v[i];
        }

        int[] indeg = new int[n + 1];
        for (int i = 0; i < m; i++) indeg[v[i]]++;

        int[] queue = new int[n + 1];
        int head = 0, tail = 0;

        for (int i = 1; i <= n; i++) {
            if (indeg[i] == 0) queue[tail++] = i;
        }

        int visited = 0;
        while (head < tail) {
            int node = queue[head++];
            visited++;
            for (int i = 0; i < deg[node]; i++) {
                indeg[adj[node][i]]--;
                if (indeg[adj[node][i]] == 0) queue[tail++] = adj[node][i];
            }
        }

        if (visited == n) {
            out.println("NO");
        } else {
            out.println("YES");
        }

        out.flush();
        data.close();
    }
}
