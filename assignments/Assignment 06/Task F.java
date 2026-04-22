import java.io.*;
import java.util.*;

public class digilock {
    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st;

        st = new StringTokenizer(data.readLine());
        int S = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        String line = data.readLine();

        if (line == null) {
            return;
        }

        int n = Integer.parseInt(line.trim());

        int[] dist = new int[10000];
        for (int i = 0; i < 10000; i++) dist[i] = -1;

        for (int i = 0; i < n; i++) {
            int forb = Integer.parseInt(data.readLine());
            dist[forb] = -2;
        }

        if (dist[C] == -2) {
            out.println(-1);
            out.flush();
            out.close();
            return;
        }

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        dist[S] = 0;
        queue.add(S);

        int[] place = {1, 10, 100, 1000};

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            if (curr == C){
                break;
            }

            for (int p = 0; p < 4; p++) {
                int digit = (curr / place[p]) % 10;

                int newDigitInc = (digit + 1) % 10;
                int inc = curr + (newDigitInc - digit) * place[p];

                int newDigitDec = (digit + 9) % 10;
                int dec = curr + (newDigitDec - digit) * place[p];

                if (dist[inc] == -1) {
                    dist[inc] = dist[curr] + 1;
                    queue.add(inc);
                }
                if (dist[dec] == -1) {
                    dist[dec] = dist[curr] + 1;
                    queue.add(dec);
                }
            }
        }

        out.println(dist[C]);
        out.flush();
        out.close();
    }
}
