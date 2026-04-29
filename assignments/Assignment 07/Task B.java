import java.io.*;
import java.util.*;

public class meetup {

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

    static long[] dijkstra(int src, int n) {
        long[] dist = new long[n + 1];
        for (int i = 0; i <= n; i++) dist[i] = Long.MAX_VALUE;
        dist[src] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));
        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.dist == dist[curr.id]) {
                for (int i = head[curr.id]; i != 0; i = next[i]) {
                    long newDist = dist[curr.id] + weight[i];
                    if (newDist < dist[dest[i]]) {
                        dist[dest[i]] = newDist;
                        pq.add(new Node(dest[i], newDist));
                    }
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
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        head = new int[n + 1];
        next = new int[m + 1];
        dest = new int[m + 1];
        weight = new long[m + 1];
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
        }

        long[] distS = dijkstra(s, n);
        long[] distT = dijkstra(t, n);

        long bestTime = Long.MAX_VALUE;
        int bestNode = -1;

        for (int i = 1; i <= n; i++) {
            if (distS[i] != Long.MAX_VALUE && distT[i] != Long.MAX_VALUE) {
                long meetTime = Math.max(distS[i], distT[i]);
                if (meetTime < bestTime) {
                    bestTime = meetTime;
                    bestNode = i;
                }
            }
        }

        out.println(bestNode == -1 ? -1 : bestTime + " " + bestNode);

        //  if (bestNode == -1) {
        //     out.println(-1);
        //  } else {
        //      out.println(bestTime + " " + bestNode);
        //  }

        out.flush();
        out.close();
    }
}
