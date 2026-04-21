import java.io.*;
import java.util.*;

public class footbol {
    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        StringTokenizer st = new StringTokenizer(data.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] head = new int[n + 1];
        int[] nxt = new int[2 * m + 1];
        int[] to = new int[2 * m + 1];
        Arrays.fill(head, 0);
        int deg = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(data.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            deg++;
            to[deg] = b;
            nxt[deg] = head[a];
            head[a] = deg;

            deg++;
            to[deg] = a;
            nxt[deg] = head[b];
            head[b] = deg;
        }

        int[] color = new int[n + 1];
        Arrays.fill(color, -1);

        long max = 0;
        ArrayDeque<Integer> q = new ArrayDeque<>();

        for (int src = 1; src <= n; src++) {
            if (color[src] != -1) continue;

            color[src] = 0;
            q.add(src);

            int[] groupSize = new int[2];
            groupSize[0] = 0;
            groupSize[1] = 0;
            boolean bipcheck = true;

            while (!q.isEmpty()) {
                int u = q.poll();
                groupSize[color[u]]++;

                for (int e = head[u]; e != 0; e = nxt[e]) {
                    int v = to[e];
                    if (color[v] == -1) {
                        color[v] = 1 - color[u];
                        q.add(v);
                    } else if (color[v] == color[u]) {
                        bipcheck = false;
                    }
                }
            }

            if (bipcheck) {
                max += Math.max(groupSize[0], groupSize[1]);
            } else {
                max += groupSize[0] + groupSize[1];
            }
        }
        out.println(max);

        out.flush();
        out.close();
    }
}
