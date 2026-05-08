import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class mission {
    static int[] parent, rank;

    static int find(int v) {
        if (v == parent[v])
            return v;
        return parent[v] = find(parent[v]);
    }

    static void make_set(int v) {
        parent[v] = v;
        rank[v] = 0;
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            if (rank[a] > rank[b]) {
                parent[b] = a;
            } else if (rank[a] < rank[b]) {
                parent[a] = b;
            } else {
                parent[b] = a; rank[a]++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st;

        st = new StringTokenizer(data.readLine());
        int t = Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(data.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int[] eu = new int[m], ev = new int[m], ew = new int[m];

            st = new StringTokenizer(data.readLine());
            for (int i = 0; i < m; i++) {
                eu[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(data.readLine());
            for (int i = 0; i < m; i++) {
                ev[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(data.readLine());
            for (int i = 0; i < m; i++) {
                ew[i] = Integer.parseInt(st.nextToken());
            }

            Integer[] order = new Integer[m];
            for (int i = 0; i < m; i++) {
                order[i] = i;
            }
            Arrays.sort(order, (a, b) -> ew[b] - ew[a]);

            parent = new int[n + 1];
            rank   = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                make_set(i);
            }

            int[] deg = new int[n + 1];
            boolean[] picked = new boolean[m];

            for (int i = 0; i < m; i++) {
                int idx = order[i];
                if (find(eu[idx]) != find(ev[idx])) {
                    union(eu[idx], ev[idx]);
                    picked[idx] = true;
                    deg[eu[idx]]++;
                    deg[ev[idx]]++;
                }
            }

            if (find(s) != find(d)) {
                out.println(0);
                continue;
            }

            int[][] adj    = new int[n + 1][];
            int[][] weight = new int[n + 1][];

            for (int i = 1; i <= n; i++) {
                adj[i]    = new int[deg[i]];
                weight[i] = new int[deg[i]];
            }

            int[] fill = new int[n + 1];
            
            for (int i = 0; i < m; i++) {
                if (picked[i]) {
                    adj[eu[i]][fill[eu[i]]]      = ev[i];
                    weight[eu[i]][fill[eu[i]]++] = ew[i];
                    adj[ev[i]][fill[ev[i]]]      = eu[i];
                    weight[ev[i]][fill[ev[i]]++] = ew[i];
                }
            }

            int[] queue = new int[n + 1];
            int[] prev  = new int[n + 1];
            int[] from  = new int[n + 1];
            Arrays.fill(prev, -1);
            prev[s] = s;
            int head = 0, tail = 0;
            queue[tail++] = s;

            while (head < tail) {
                int curr = queue[head++];
                for (int j = 0; j < deg[curr]; j++) {
                    int nb = adj[curr][j];
                    if (prev[nb] == -1) {
                        prev[nb] = curr;
                        from[nb] = weight[curr][j];
                        queue[tail++] = nb;
                    }
                }
            }

            int minEdge = Integer.MAX_VALUE;
            int cur = d;
            while (cur != s) {
                if (from[cur] < minEdge) minEdge = from[cur];
                cur = prev[cur];
            }

            out.println(minEdge);
        }

        out.flush();
        data.close();
    }
}
