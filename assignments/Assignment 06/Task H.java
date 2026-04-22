import java.io.*;
import java.util.*;

public class caveman {
    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st;

        int n = Integer.parseInt(data.readLine().trim());
        String[] words = new String[n];

        for (int i = 0; i < n; i++) {
            words[i] = data.readLine().trim();
        }

        boolean[] exists = new boolean[26];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                exists[words[i].charAt(j) - 'a'] = true;
            }
        }

        int[] start = new int[26];
        int[] next  = new int[26 * 26 + 1];
        int[] to   = new int[26 * 26 + 1];
        int[] degin = new int[26];
        boolean[][] added = new boolean[26][26];
        int edgeCount = 0;

        for (int i = 0; i < n - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            int minLen = Math.min(w1.length(), w2.length());
            boolean found = false;

            for (int j = 0; j < minLen; j++) {
                int c1 = w1.charAt(j) - 'a';
                int c2 = w2.charAt(j) - 'a';
                if (c1 != c2) {
                    if (!added[c1][c2]) {
                        added[c1][c2] = true;
                        edgeCount++;
                        to[edgeCount] = c2;
                        next[edgeCount] = start[c1];
                        start[c1] = edgeCount;
                        degin[c2]++;
                    }
                    found = true;
                    break;
                }
            }

            if (!found && w1.length() > w2.length()) {
                out.println(-1);
                out.flush();
                out.close();
                return;
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < 26; i++) {
            if (exists[i] && degin[i] == 0) {
                pq.add(i);
            }
        }

        char[] result = new char[26];
        int pos = 0;

        while (!pq.isEmpty()) {
            int u = pq.poll();
            result[pos++] = (char)('a' + u);

            for (int e = start[u]; e != 0; e = next[e]) {
                int v = to[e];
                degin[v]--;
                if (degin[v] == 0 && exists[v]) {
                    pq.add(v);
                }
            }
        }

        int total = 0;
        for (int i = 0; i < 26; i++) {
            if (exists[i]) total++;
        }

        if (pos < total) {
            out.println(-1);
        } else {
            out.println(new String(result, 0, pos));
        }

        out.flush();
        out.close();
    }
}
