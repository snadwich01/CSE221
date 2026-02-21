import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class havefun {
    public static void main(String[] args) throws IOException {
        BufferedReader datain = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String first = datain.readLine();
        if (first == null){
            return;
        }

        long T = Long.parseLong(first.trim());

        for (long t = 0; t < T; t++) {
            String line = datain.readLine();
            if (line == null){
                break;
            }
            
            StringTokenizer token = new StringTokenizer(line);

            long k = Long.parseLong(token.nextToken());
            long x = Long.parseLong(token.nextToken());

            long low = 1;
            long high = 2000000000;
            long ans = high;

            while (low <= high) {
                long mid = (low + high) / 2;
                long count = mid - (mid / x);

                if (count >= k) {
                    ans = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            
            out.println(ans);
        }

        out.flush();
        datain.close();
    }
}
