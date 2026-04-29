import java.io.*;
import java.util.*;

public class mindan {

    static int[] head, next, dest;
    static long[] weight;

    static class Node {
        int id;
        long dist;

        Node(int id, long dist) {
            this.id = id;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st;

        st = new StringTokenizer(data.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        head = new int[n + 1];
        next = new int[2 * m + 1];
        dest = new int[2 * m + 1];
        weight = new long[2 * m + 1];
        int eC = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(data.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());

            eC++;
            dest[eC] = v;
            weight[eC] = w;
            next[eC] = head[u];
            head[u] = eC;

            eC++;
            dest[eC] = u;
            weight[eC] = w;
            next[eC] = head[v];
            head[v] = eC;
        }

        long[] dist = new long[n + 1];
        for (int i = 0; i <= n; i++) dist[i] = Long.MAX_VALUE;
        dist[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.dist == dist[curr.id]) {
                for (int e = head[curr.id]; e != 0; e = next[e]) {
                    long newDist = Math.max(dist[curr.id], weight[e]);
                    if (newDist < dist[dest[e]]) {
                        dist[dest[e]] = newDist;
                        pq.add(new Node(dest[e], newDist));
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (i > 1) out.print(' ');
            if (dist[i] == Long.MAX_VALUE) {
                out.print(-1);
            } else {
                out.print(dist[i]);
            }
        }
        out.println();

        out.flush();
        out.close();
    }
}
