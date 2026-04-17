import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class reachability {
    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = null;

        String line = data.readLine();
        if (line == null) return;
        st = new StringTokenizer(line);
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[] u = new int[m], v = new int[m];
        int[] cnt = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(data.readLine());
            u[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
            cnt[u[i]]++;
            cnt[v[i]]++;
        }

        int[][] adj = new int[n + 1][];
        for (int i = 1; i <= n; i++){
            adj[i] = new int[cnt[i]];
        }

        int[] idx = new int[n + 1];
        for (int i = 0; i < m; i++) {
            adj[u[i]][idx[u[i]]++] = v[i];
            adj[v[i]][idx[v[i]]++] = u[i];
        }

        int[] comp = new int[n + 1];
        int[] queue = new int[n + 1];
        int id = 0;

        for (int i = 1; i <= n; i++) {
            if (comp[i] == 0) {
                id++;
                int head = 0, tail = 0;
                comp[i] = id;
                queue[tail++] = i;
                
                while (head < tail) {
                    int node = queue[head++];
                    for (int j = 0; j < cnt[node]; j++) {
                        if (comp[adj[node][j]] == 0) {
                            comp[adj[node][j]] = id;
                            queue[tail++] = adj[node][j];
                        }
                    }
                }
            }
        }

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(data.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (comp[x] == comp[y]) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }

        out.flush();
        data.close();
    }
}
