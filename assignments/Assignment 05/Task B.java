import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class dfs {
    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st;

        st = new StringTokenizer(data.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] src = new int[m];
        int[] dest = new int[m];
        int[] deg = new int[n + 1];

    
        st = new StringTokenizer(data.readLine());
        for (int i = 0; i < m; i++) src[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(data.readLine());
        for (int i = 0; i < m; i++) dest[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            deg[src[i]]++;
            deg[dest[i]]++;
        }

        int[][] adj = new int[n + 1][];
        for (int i = 1; i <= n; i++) adj[i] = new int[deg[i]];

        int[] pos = new int[n + 1];
        for (int i = 0; i < m; i++) {
            adj[src[i]][pos[src[i]]++] = dest[i];
            adj[dest[i]][pos[dest[i]]++] = src[i];
        }

        
        boolean[] visited = new boolean[n + 1];
        int[] stack = new int[n + 1];
        int[] pointer = new int[n + 1];
        int top = 0;

        visited[1] = true;
        stack[top++] = 1;
        out.print(1);

        while (top > 0) {
            int node = stack[top - 1];
            boolean pushed = false;

            while (pointer[node] < deg[node]) {
                int next = adj[node][pointer[node]++];
                if (!visited[next]) {
                    visited[next] = true;
                    stack[top++] = next;

                    out.print(" " + next);
                    pushed = true;
                    break;
                }
            }

            if (!pushed) top--;
        }

        out.println();
        out.flush();
        data.close();
    }
}
