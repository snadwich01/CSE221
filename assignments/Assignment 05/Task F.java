import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class treeq {
    static int[][] adj;
    static int[] deg;
    static int[] size;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = null;

        String line = data.readLine();
        if (line == null) return;
        st = new StringTokenizer(line);
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[] u = new int[n - 1], v = new int[n - 1];
        deg = new int[n + 1];

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(data.readLine());
            u[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());

            deg[u[i]]++;
            deg[v[i]]++;
        }

        adj = new int[n + 1][];
        for (int i = 1; i <= n; i++) adj[i] = new int[deg[i]];

        int[] pos = new int[n + 1];
        for (int i = 0; i < n - 1; i++) {
            adj[u[i]][pos[u[i]]++] = v[i];
            adj[v[i]][pos[v[i]]++] = u[i];
        }

        size = new int[n + 1];
        visited = new boolean[n + 1];

        dfs(r);

        int q = Integer.parseInt(data.readLine().trim());
        for (int i = 0; i < q; i++) {
            int x = Integer.parseInt(data.readLine().trim());
            out.println(size[x]);
        }

        out.flush();
        data.close();
    }

    static void dfs(int node) {
        visited[node] = true;
        size[node] = 1;

        for (int i = 0; i < deg[node]; i++) {
            int next = adj[node][i];
            
            if (!visited[next]) {
                dfs(next);
                size[node] += size[next];
            }
        }
    }
}
