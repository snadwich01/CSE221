import java.io.*;
import java.util.*;

public class neart {

    static int[] head, nxt, to;
    static int n;

    static int[] bfs(int[] sources) {
        int[] dist = new int[n + 1];
        for (int i = 0; i <= n; i++) dist[i] = -1;

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < sources.length; i++) {
            dist[sources[i]] = 0;
            queue.add(sources[i]);
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int e = head[u]; e != 0; e = nxt[e]) {
                int v = to[e];
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    queue.add(v);
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st;

        st = new StringTokenizer(data.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        head = new int[n + 1];
        nxt  = new int[2 * m + 1];
        to   = new int[2 * m + 1];
        int edgeCount = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(data.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            edgeCount++;
            to[edgeCount] = b;
            nxt[edgeCount] = head[a];
            head[a] = edgeCount;

            edgeCount++;
            to[edgeCount] = a;
            nxt[edgeCount] = head[b];
            head[b] = edgeCount;
        }

        st = new StringTokenizer(data.readLine());
        int[] sources = new int[s];
        for (int i = 0; i < s; i++) {
            sources[i] = Integer.parseInt(st.nextToken());
        }

        int[] dist = bfs(sources);

        st = new StringTokenizer(data.readLine());
        for (int i = 0; i < q; i++) {
            int dest = Integer.parseInt(st.nextToken());
            if (i > 0) out.print(' ');
            out.print(dist[dest]);
        }
        out.println();
        out.flush();
        out.close();
    }
}
