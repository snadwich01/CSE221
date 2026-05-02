import java.io.*;
import java.util.*;

public class SS {

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
        int s = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

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

        long[][] dist = new long[n + 1][2];
        for (int i = 0; i <= n; i++) {
            dist[i][0] = -1;
            dist[i][1] = -1;
        }

        int[] count = new int[n + 1];

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));
        
        dist[s][0] = 0;
        pq.add(new Node(s, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            count[curr.id]++;

            if (count[curr.id] <= 2) {
                for (int e = head[curr.id]; e != 0; e = next[e]) {
                    long newDist = curr.dist + weight[e];
                    int v = dest[e];

                    if (dist[v][0] == -1 || newDist < dist[v][0]) {
                        dist[v][1] = dist[v][0];
                        dist[v][0] = newDist;
                        pq.add(new Node(v, newDist));
                    } else if (newDist != dist[v][0] && (dist[v][1] == -1 || newDist < dist[v][1])) {
                        dist[v][1] = newDist;
                        pq.add(new Node(v, newDist));
                    }
                }
            }
        }

        out.println(dist[d][1]);
        out.flush();
        out.close();
    }
}
