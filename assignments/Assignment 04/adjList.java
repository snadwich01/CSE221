import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class adjListRep {
    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = null;

        String line = data.readLine();
        if (line == null) return;
        st = new StringTokenizer(line);
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] u = new int[m], v = new int[m], w = new int[m];

        st = new StringTokenizer(data.readLine());
        for (int i = 0; i < m; i++) u[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(data.readLine());
        for (int i = 0; i < m; i++) v[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(data.readLine());
        for (int i = 0; i < m; i++) w[i] = Integer.parseInt(st.nextToken());

        int[][] adj = new int[n + 1][m * 2];
        int[] deg = new int[n + 1];
        int[][] weight = new int[n + 1][m * 2];

        for (int i = 0; i < m; i++) {
            adj[u[i]][deg[u[i]]] = v[i];
            weight[u[i]][deg[u[i]]] = w[i];
            deg[u[i]]++;
        }

        for (int i = 1; i <= n; i++) {
            out.print(i + ":");
            for (int j = 0; j < deg[i]; j++) out.print(" (" + adj[i][j] + "," + weight[i][j] + ")");
            out.print("\n");
        }

        out.flush();
        data.close();
    }
}
