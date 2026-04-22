import java.io.*;
import java.util.*;

public class diam {

    static int[] head, nxt, to;

    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st;

        int n = Integer.parseInt(data.readLine().trim());
        int m = n - 1;

        head = new int[n + 1];
        nxt = new int[2 * m + 1];
        to = new int[2 * m + 1];
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

        int[] dist1 = bfs(1, n);
        int src = 1;
        for (int i = 2; i <= n; i++) {
            if (dist1[i] > dist1[src]) src = i;
        }

        int[] dist2 = bfs(src, n);
        int dest = 1;
        for (int i = 2; i <= n; i++) {
            if (dist2[i] > dist2[dest]) dest = i;
        }

        out.println(dist2[dest]);
        out.println(src + " " + dest);

        out.flush();
        out.close();
    }

    static int[] bfs(int src, int n) {
        int[] dist = new int[n + 1];
        for (int i = 0; i <= n; i++) dist[i] = -1;

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        dist[src] = 0;
        queue.add(src);

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
}
