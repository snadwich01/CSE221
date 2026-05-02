import java.io.*;
import java.util.*;

public class Main {

    static int[] head, next, dest;
    static long[] weight;

    static class Node {
        int id;
        int parity;
        long dist;

        Node(int id, int parity, long dist) {
            this.id = id;
            this.parity = parity;
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

        int[] from = new int[m];
        int[] to = new int[m];
        long[] wt = new long[m];

        st = new StringTokenizer(data.readLine());
        for (int i = 0; i < m; i++) {
            from[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(data.readLine());
        for (int i = 0; i < m; i++) {
            to[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(data.readLine());
        for (int i = 0; i < m; i++) {
            wt[i] = Long.parseLong(st.nextToken());
        }

        head = new int[n + 1];
        next = new int[m + 1];
        dest = new int[m + 1];
        weight = new long[m + 1];
        int eC = 0;

        for (int i = 0; i < m; i++) {
            eC++;
            dest[eC] = to[i];
            weight[eC] = wt[i];
            next[eC] = head[from[i]];
            head[from[i]] = eC;
        }

        long[][] dist = new long[n + 1][2];
        for (int i = 0; i <= n; i++) {
            dist[i][0] = -1;
            dist[i][1] = -1;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));

        for (int e = head[1]; e != 0; e = next[e]) {
            int p = (int)(weight[e] % 2);
            
            if (dist[dest[e]][p] == -1 || weight[e] < dist[dest[e]][p]) {
                dist[dest[e]][p] = weight[e];
                pq.add(new Node(dest[e], p, weight[e]));
            }
        }

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.dist != dist[curr.id][curr.parity]) continue;

            for (int e = head[curr.id]; e != 0; e = next[e]) {
                int ep = (int)(weight[e] % 2);

                if (ep != curr.parity) {
                    long newDist = dist[curr.id][curr.parity] + weight[e];
                    if (dist[dest[e]][ep] == -1 || newDist < dist[dest[e]][ep]) {
                        dist[dest[e]][ep] = newDist;
                        pq.add(new Node(dest[e], ep, newDist));
                    }
                }
            }
        }

        long ans = -1;
        if (dist[n][0] != -1 && dist[n][1] != -1) {
            ans = Math.min(dist[n][0], dist[n][1]);
        } else if (dist[n][0] != -1) {
            ans = dist[n][0];
        } else if (dist[n][1] != -1) {
            ans = dist[n][1];
        }

        out.println(ans);
        out.flush();
        out.close();
    }
}
