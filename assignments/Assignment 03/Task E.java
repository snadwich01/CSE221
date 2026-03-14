import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class seriesDrift {
    
    static long a, n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String line = data.readLine();
        if (line == null) return;
        
        int cases = Integer.parseInt(line);

        for (int i = 0; i < cases; i++) {
            StringTokenizer st = new StringTokenizer(data.readLine());
            a = Long.parseLong(st.nextToken());
            n = Long.parseLong(st.nextToken());
            m = Long.parseLong(st.nextToken());

            if (a % m == 0) {
                out.println(0);
            } else if (a % m == 1) {
                out.println(n % m);
            } else {
                out.println(calculateSeries(a % m, n, m));
            }
        }

        out.flush();
        data.close();
    }

    public static long calculateSeries(long ratio, long terms, long mod) {
        if (terms == 0) return 0;
        if (terms == 1) return ratio % mod;

        long halfCount = terms / 2;
        long firstHalfSum = calculateSeries(ratio, halfCount, mod);
        
        long jumpFactor = 1;
        long base = ratio;
        long exponent = halfCount;
        
        while (exponent > 0) {
            if (exponent % 2 == 1) jumpFactor = (jumpFactor * base) % mod;
            base = (base * base) % mod;
            exponent /= 2;
        }

        long totalSum = (firstHalfSum + (jumpFactor * firstHalfSum) % mod) % mod;

        if (terms % 2 != 0) {
            long lastTerm = (((jumpFactor * jumpFactor) % mod) * ratio) % mod;
            totalSum = (totalSum + lastTerm) % mod;
        }

        return totalSum;
    }
}
