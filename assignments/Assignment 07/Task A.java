import java.io.*;
import java.util.*;

public class sssp {

    static int[] head, next, to;
    static long[] weight;

    static class Node {
        int id;
        long dist;

        Node(int id, long dist) {
            this.id   = id;
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
        int s = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        head   = new int[n + 1];
        next    = new int[m + 1];
        to     = new int[m + 1];
        weight = new long[m + 1];
        int eC = 0;

        int[]  u = new int[m];
        int[]  v = new int[m];
        long[] w = new long[m];

        st = new StringTokenizer(data.readLine());
        for (int i = 0; i < m; i++) u[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(data.readLine());
        for (int i = 0; i < m; i++) v[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(data.readLine());
        for (int i = 0; i < m; i++) w[i] = Long.parseLong(st.nextToken());

        for (int i = 0; i < m; i++) {
            eC++;
            to[eC]     = v[i];
            weight[eC] = w[i];
            next[eC]    = head[u[i]];
            head[u[i]]        = eC;
        }

        long[] dist = new long[n + 1];
        int[]  prev = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dist[i] = Long.MAX_VALUE;
            prev[i] = -1;
        }

        dist[s] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));
        pq.add(new Node(s, 0));

        while (!pq.isEmpty()) {
            Node curr  = pq.poll();
            int  uid   = curr.id;
            long udist = curr.dist;

            if (udist > dist[uid]) continue;

            if (uid == d) break;

            for (int e = head[uid]; e != 0; e = next[e]) {
                int  vid     = to[e];
                long newDist = dist[uid] + weight[e];
                if (newDist < dist[vid]) {
                    dist[vid] = newDist;
                    prev[vid] = uid;
                    pq.add(new Node(vid, newDist));
                }
            }
        }

        if (dist[d] == Long.MAX_VALUE) {
            out.println(-1);
        } else {
            out.println(dist[d]);

            int[] path   = new int[n + 1];
            int pathLen  = 0;
            int cur      = d;
            while (cur != -1) {
                path[pathLen++] = cur;
                cur = prev[cur];
            }

            for (int i = pathLen - 1; i >= 0; i--) {
                if (i < pathLen - 1) out.print(' ');
                out.print(path[i]);
            }
            out.println();
        }

        out.flush();
        out.close();
    }
}
