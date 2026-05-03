import java.io.*;
import java.util.*;

public class beauty {

    static int[] head, next, dest;
    static long[] weight; // edge weights

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
        int s = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        long[] nodeWeight = new long[n + 1];
        st = new StringTokenizer(data.readLine());

        for (int i = 1; i <= n; i++) {
            nodeWeight[i] = Long.parseLong(st.nextToken());
        }

        head = new int[n + 1];
        next = new int[m + 1];
        dest = new int[m + 1];
        weight = new long[m + 1]; // NEW
        int eC = 0;

        // input: u v w
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(data.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());

            eC++;
            dest[eC] = v;
            weight[eC] = w; // store edge weight
            next[eC] = head[u];
            head[u] = eC;
        }

        long[] dist = new long[n + 1];
        Arrays.fill(dist, -1);

        dist[s] = nodeWeight[s];

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));
        pq.add(new Node(s, dist[s]));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (curr.dist != dist[curr.id]) continue;

            for (int e = head[curr.id]; e != 0; e = next[e]) {
                int v = dest[e];

                long newDist = dist[curr.id] + weight[e] + nodeWeight[v];

                if (dist[v] == -1 || newDist < dist[v]) {
                    dist[v] = newDist;
                    pq.add(new Node(v, newDist));
                }
            }
        }

        out.println(dist[d] == -1 ? -1 : dist[d]);

        out.flush();
        out.close();
    }
}