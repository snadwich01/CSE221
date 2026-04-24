import java.io.*;
import java.util.*;

public class connect {
    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int t = Integer.parseInt(data.readLine().trim());

        for (int test = 0; test < t; test++) {
            StringTokenizer st = new StringTokenizer(data.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] head = new int[n + 1];
            int[] nxt = new int[m + 1];
            int[] to = new int[m + 1];
            Arrays.fill(head, 0);
            int edgeCount = 0;

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(data.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                edgeCount++;
                to[edgeCount] = b;
                nxt[edgeCount] = head[a];
                head[a] = edgeCount;
            }

            int[] result = solve(n, head, nxt, to);

            if (result == null) {
                out.println(-1);
            } else {
                for (int i = 0; i < result.length; i++) {
                    if (i > 0) out.print(' ');
                    out.print(result[i]);
                }
                out.println();
            }
        }

        out.flush();
        out.close();
    }

    static int[] solve(int n, int[] head, int[] nxt, int[] to) {
        int[] prereqsLeft = new int[n + 1];

        for (int u = 1; u <= n; u++) {
            for (int e = head[u]; e != 0; e = nxt[e]) {
                prereqsLeft[to[e]]++;
            }
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int u = 1; u <= n; u++) {
            if (prereqsLeft[u] == 0) {
                q.addLast(u);
            }
        }

        int[] ordering = new int[n];
        int pos = 0;

        while (!q.isEmpty()) {
            int u = q.pollFirst();
            ordering[pos] = u;
            pos++;
            for (int e = head[u]; e != 0; e = nxt[e]) {
                int v = to[e];
                prereqsLeft[v]--;
                if (prereqsLeft[v] == 0) {
                    q.addLast(v);
                }
            }
        }

        if (pos < n) return null;
        return ordering;
    }
}
