import java.io.*;
import java.util.*;

public class helpking {
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
            if (rank[a] > rank[b]) { parent[b] = a; }
            else if (rank[a] < rank[b]) { parent[a] = b; }
            else { parent[b] = a; rank[a]++; }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st;

        st = new StringTokenizer(data.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] edges = new int[m][3];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(data.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        parent = new int[n + 1];
        rank   = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            make_set(i);
        }

        Arrays.sort(edges, (a, b) -> a[2] - b[2]);

        long total = 0;
        for (int i = 0; i < m; i++) {
            int u = edges[i][0], v = edges[i][1], w = edges[i][2];
            if (find(u) != find(v)) {
                total += w;
                union(u, v);
            }
        }

        out.println(total);

        out.flush();
        data.close();
    }
}
