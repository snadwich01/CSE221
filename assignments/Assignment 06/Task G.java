import java.io.*;
import java.util.*;

public class wordle {
    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st;

        st = new StringTokenizer(data.readLine());
        int n = Integer.parseInt(st.nextToken());
        String a = st.nextToken();
        String b = st.nextToken();

        String[] vocab = new String[n];
        for (int i = 0; i < n; i++) {
            vocab[i] = data.readLine().trim();
        }

        int[] ldeg = new int[26];
        for (int i = 0; i < n; i++) {
            ldeg[vocab[i].charAt(0) - 'A']++;
        }

        int[][] l = new int[26][];
        for (int i = 0; i < 26; i++) {
            l[i] = new int[ldeg[i]];
        }

        int[] lpos = new int[26];
        for (int i = 0; i < n; i++) {
            int c = vocab[i].charAt(0) - 'A';
            l[c][lpos[c]++] = i;
        }

        int start = -1, end = -1;
        for (int i = 0; i < n; i++) {
            if (vocab[i].equals(a)) start = i;
            if (vocab[i].equals(b)) end = i;
        }

        boolean[] visited = new boolean[n];
        boolean[] done = new boolean[26];

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int u = queue.poll();

            if (u == end) {
                out.println("YES");
                out.flush();
                out.close();
                return;
            }

            int last = vocab[u].charAt(vocab[u].length() - 1) - 'A';

            if (!done[last]) {
                done[last] = true;
                for (int i = 0; i < l[last].length; i++) {
                    int v = l[last][i];
                    if (!visited[v]) {
                        visited[v] = true;
                        queue.add(v);
                    }
                }
            }
        }

        out.println("NO");
        out.flush();
        out.close();
    }
}
