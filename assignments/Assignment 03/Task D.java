import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class matDrift {
    static long MOD = 1000000007L;

    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        String line = data.readLine();
        if (line == null) return;
        
        int T = Integer.parseInt(line);

        for (int t = 0; t < T; t++) {
            StringTokenizer parser = new StringTokenizer(data.readLine());
            long[] A = new long[4];
            for (int i = 0; i < 4; i++) {
                A[i] = Long.parseLong(parser.nextToken()) % MOD;
            }

            long X = Long.parseLong(data.readLine());

            long[] res = {1, 0, 0, 1};

            while (X > 0) {
                if (X % 2 == 1) {
                    res = multiply(res, A);
                }
                A = multiply(A, A);
                X /= 2;
            }

            out.println(res[0] + " " + res[1]);
            out.println(res[2] + " " + res[3]);
        }
        
        out.flush();
        data.close();
    }

    public static long[] multiply(long[] A, long[] B) {
        long[] rest = new long[4];
        rest[0] = (A[0] * B[0] + A[1] * B[2]) % MOD;
        rest[1] = (A[0] * B[1] + A[1] * B[3]) % MOD;
        rest[2] = (A[2] * B[0] + A[3] * B[2]) % MOD;
        rest[3] = (A[2] * B[1] + A[3] * B[3]) % MOD;
        return rest;
    }
}
