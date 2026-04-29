import java.io.*;
import java.util.*;

public class beauty {

    static int[] head, next, dest;

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
        int eC = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(data.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            eC++;
            dest[eC] = v;
            next[eC] = head[u];
            head[u] = eC;
        }

        long[] dist = new long[n + 1];
        for (int i = 0; i <= n; i++) dist[i] = -1;
        dist[s] = nodeWeight[s];

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));
        pq.add(new Node(s, nodeWeight[s]));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.dist == dist[curr.id]) {
                for (int e = head[curr.id]; e != 0; e = next[e]) {
                    long newDist = dist[curr.id] + nodeWeight[dest[e]];
                    if (dist[dest[e]] == -1 || newDist < dist[dest[e]]) {
                        dist[dest[e]] = newDist;
                        pq.add(new Node(dest[e], newDist));
                    }
                }
            }
        }

        out.println(dist[d]);
        
        out.flush();
        out.close();
    }
}
