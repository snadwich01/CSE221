import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader datain = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        String first = datain.readLine();
        if (first == null) {
            return;
        }
        
        StringTokenizer alsize = new StringTokenizer(first);
        int N = Integer.parseInt(alsize.nextToken());

        int[] alice = new int[N];
        StringTokenizer token1 = new StringTokenizer(datain.readLine());
        for (int i = 0; i < N; i++) {
            alice[i] = Integer.parseInt(token1.nextToken());
        }

        String third = datain.readLine();
        if (third == null){
            return;
        }

        StringTokenizer bobsize = new StringTokenizer(third);
        int M = Integer.parseInt(bobsize.nextToken());

        int[] bob = new int[M];
        StringTokenizer token2 = new StringTokenizer(datain.readLine());

        for (int i = 0; i < M; i++) {
            bob[i] = Integer.parseInt(token2.nextToken());
        }

        int i = 0;
        int j = 0;
        int[] combine = new int[N + M];
        
        for (int k = 0; k < N + M; k++) {
            if (i < N && j < M) {
                if (alice[i] < bob[j]) {
                    combine[k] = alice[i];
                    i += 1;
                } else {
                    combine[k] = bob[j];
                    j += 1;
                }
            } else if (i < N) {
                combine[k] = alice[i];
                i += 1;
            } else {
                combine[k] = bob[j];
                j += 1;
            }
        }

        for (int idx = 0; idx < combine.length; idx++) {
            out.print(combine[idx]);
            if (idx < combine.length - 1) {
                out.print(" ");
            }
        }
        out.println();

        out.flush();
        datain.close();
    }
}
