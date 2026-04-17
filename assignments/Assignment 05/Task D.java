import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class jungle {
    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st;

        st = new StringTokenizer(data.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] from = new int[m];
        int[] to = new int[m];

        int[] outDeg = new int[n + 1];
        int[] inDeg = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(data.readLine());
            from[i] = Integer.parseInt(st.nextToken());
            to[i] = Integer.parseInt(st.nextToken());
            outDeg[from[i]]++;
            inDeg[to[i]]++;
        }

        int[][] adj = new int[n + 1][];
        for (int i = 1; i <= n; i++) adj[i] = new int[outDeg[i]];

        int[] pos = new int[n + 1];
        for (int i = 0; i < m; i++) {
            adj[from[i]][pos[from[i]]++] = to[i];
        }

        int[][] rev = new int[n + 1][];
        for (int i = 1; i <= n; i++) rev[i] = new int[inDeg[i]];

        int[] rpos = new int[n + 1];
        for (int i = 0; i < m; i++) {
            rev[to[i]][rpos[to[i]]++] = from[i];
        }

        int[] distSrc = new int[n + 1];
        int[] parentS = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            distSrc[i] = -1;
            parentS[i] = -1;
        }

        int[] queue = new int[n + 1];
        int head = 0, tail = 0;

        distSrc[s] = 0;
        queue[tail++] = s;

        while (head < tail) {
            int node = queue[head++];
            for (int i = 0; i < outDeg[node]; i++) {
                int nb = adj[node][i];
                if (distSrc[nb] == -1) {
                    distSrc[nb] = distSrc[node] + 1;
                    parentS[nb] = node;
                    queue[tail++] = nb;
                }
            }
        }

        int[] distDest = new int[n + 1];
        int[] parentD = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            distDest[i] = -1;
            parentD[i] = -1;
        }

        head = 0;
        tail = 0;

        distDest[d] = 0;
        queue[tail++] = d;

        while (head < tail) {
            int node = queue[head++];
            for (int i = 0; i < inDeg[node]; i++) {
                int nb = rev[node][i];
                if (distDest[nb] == -1) {
                    distDest[nb] = distDest[node] + 1;
                    parentD[nb] = node;
                    queue[tail++] = nb;
                }
            }
        }

        if (distSrc[k] == -1 || distDest[k] == -1) {
            out.println(-1);
            out.flush();
            return;
        }

        int[] path1 = new int[distSrc[k] + 1];
        int cur = k;

        for (int i = distSrc[k]; i >= 0; i--) {
            path1[i] = cur;
            cur = parentS[cur];
        }

        int[] path2 = new int[distDest[k] + 1];
        cur = k;

        for (int i = 0; i <= distDest[k]; i++) {
            path2[i] = cur;
            cur = parentD[cur];
        }

        int total = distSrc[k] + distDest[k];
        out.println(total);

        for (int i = 0; i <= distSrc[k]; i++) {
            out.print(path1[i] + " ");
        }

        for (int i = 1; i <= distDest[k]; i++) {
            out.print(path2[i]);
            if (i < distDest[k]) out.print(" ");
        }

        out.println();
        out.flush();
    }
}
