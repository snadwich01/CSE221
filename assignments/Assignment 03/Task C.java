import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class powerDrift {
    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String ab = data.readLine();
        if (ab == null) return;
        
        StringTokenizer st = new StringTokenizer(ab);
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long mod = 107;

        long result = 1;
        a %= mod;

        while (b > 0) {
            if (b % 2 == 1) {
                result = (result * a) % mod;
            }
            a = (a * a) % mod;
            b /= 2;
        }

        out.println(result);
        
        out.flush();
        data.close();
    }
}
