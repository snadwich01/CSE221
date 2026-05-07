import java.io.*;
import java.util.*;

public class friendship {
    static int[] parent, rank, size;

    static int find(int v) {
        if (v == parent[v]) {
                return v;
        }

        return parent[v] = find(parent[v]);
    }

    static void make_set(int v) {
        parent[v] = v;
        rank[v] = 0;
        size[v] = 1;
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            if (rank[a] > rank[b]) {
                parent[b] = a; size[a] += size[b];
            } else if (rank[a] < rank[b]) {
                parent[a] = b; size[b] += size[a];
            } else {
                parent[b] = a; size[a] += size[b]; rank[a]++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st;

        st = new StringTokenizer(data.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        rank   = new int[n + 1];
        size   = new int[n + 1];
        
        for (int i = 1; i <= n; i++){
            make_set(i);
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(data.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
            out.println(size[find(a)]);
        }

        out.flush();
        data.close();
    }
}
