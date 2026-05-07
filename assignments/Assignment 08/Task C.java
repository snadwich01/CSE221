import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class mst2 {
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
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] eu = new int[m], ev = new int[m], ew = new int[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(data.readLine());
            eu[i] = Integer.parseInt(st.nextToken());
            ev[i] = Integer.parseInt(st.nextToken());
            ew[i] = Integer.parseInt(st.nextToken());
        }

        Integer[] order = new Integer[m];
        for (int i = 0; i < m; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> ew[a] - ew[b]);

        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            make_set(i);
        }

        boolean[] picked = new boolean[m];
        long mstCost = 0;
        for (int i = 0; i < m; i++) {
            int idx = order[i];
            if (find(eu[idx]) != find(ev[idx])) {
                union(eu[idx], ev[idx]);
                picked[idx] = true;
                mstCost += ew[idx];
            }
        }

        int[] deg = new int[n + 1];
        for (int i = 0; i < m; i++) {
            if (picked[i]) {
                deg[eu[i]]++;
                deg[ev[i]]++;
            }
        }

        int[][] adj = new int[n + 1][];
        int[][] weight = new int[n + 1][];
        for (int i = 1; i <= n; i++) {
            adj[i] = new int[deg[i]];
            weight[i] = new int[deg[i]];
        }

        int[] fill = new int[n + 1];
        for (int i = 0; i < m; i++) {
            if (picked[i]) {
                adj[eu[i]][fill[eu[i]]] = ev[i];
                weight[eu[i]][fill[eu[i]]++] = ew[i];
                adj[ev[i]][fill[ev[i]]] = eu[i];
                weight[ev[i]][fill[ev[i]]++] = ew[i];
            }
        }

        int[] queue = new int[n + 1];
        int[] parent = new int[n + 1];
        int[] from = new int[n + 1];

        long best = Long.MAX_VALUE;

        for (int i = 0; i < m; i++) {
            if (picked[i]) continue;

            int src = eu[i], dst = ev[i], w = ew[i];

            Arrays.fill(parent, -1);
            Arrays.fill(from, 0);
            int head = 0, tail = 0;
            parent[src] = src;
            queue[tail++] = src;

            while (head < tail) {
                int curr = queue[head++];
                for (int j = 0; j < deg[curr]; j++) {
                    int nb = adj[curr][j];
                    if (parent[nb] == -1) {
                        parent[nb]  = curr;
                        from[nb] = weight[curr][j];
                        queue[tail++] = nb;
                    }
                }
            }

            int maxEdge = 0;
            int cur = dst;
            while (cur != src) {
                if (from[cur] > maxEdge) maxEdge = from[cur];
                cur = parent[cur];
            }

            long candidate = mstCost - maxEdge + w;
            if (candidate > mstCost && candidate < best) {
                best = candidate;
            }
        }

        if (best == Long.MAX_VALUE) {
            out.println(-1);
        } else {
            out.println(best);
        }

        out.flush();
        data.close();
    }
}
