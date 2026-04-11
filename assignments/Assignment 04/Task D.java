import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class oiler {
    static int[] rt, depth;

    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = null;

        String line = data.readLine();
        if (line == null) return;
        st = new StringTokenizer(line);
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] start = new int[m], dest = new int[m], deg = new int[n + 1];
        rt = new int[n + 1];
        depth = new int[n + 1];
        for (int i = 1; i <= n; i++) rt[i] = i;

        st = new StringTokenizer(data.readLine());
        for (int i = 0; i < m; i++) start[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(data.readLine());
        for (int i = 0; i < m; i++) dest[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            if (start[i] == dest[i]) {
                deg[start[i]] += 2;
            } else {
                deg[start[i]]++;
                deg[dest[i]]++;
                union(start[i], dest[i]);
            }
        }

        int oddDeg = 0;
        for (int i = 1; i <= n; i++) if (deg[i] % 2 != 0) oddDeg++;

        int components = 0;
        for (int i = 1; i <= n; i++) if (deg[i] > 0 && find(i) == i) components++;

        if (components > 1) {
            out.println("NO");
        } else if (oddDeg == 0 || oddDeg == 2) {
            out.println("YES");
        } else {
            out.println("NO");
        }

        out.flush();
        data.close();
    }

    static int find(int x) {
        if (rt[x] != x) rt[x] = find(rt[x]);

        return rt[x];
    }

    static void union(int start, int dest) {
        int ru = find(start), rv = find(dest);

        if (ru == rv) return;

        if (depth[ru] < depth[rv]) { int temp = ru; ru = rv; rv = temp; }
        rt[rv] = ru;
        if (depth[ru] == depth[rv]) depth[ru]++;
    }
}
