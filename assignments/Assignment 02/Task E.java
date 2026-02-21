import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LSS {
    public static void main(String[] args) throws IOException {
        BufferedReader datain = new BufferedReader(new InputStreamReader(System.in));
        String first = datain.readLine();
        if (first == null) return;
        
        StringTokenizer tok1 = new StringTokenizer(first);
        int N = Integer.parseInt(tok1.nextToken());
        long K = Long.parseLong(tok1.nextToken());

        int[] a = new int[N];
        StringTokenizer tok2 = new StringTokenizer(datain.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(tok2.nextToken());
        }

        int best = 0;
        long sum = 0;
        int left = 0;

        for (int right = 0; right < N; right++) {
            sum += a[right];

            while (sum > K && left <= right) {
                sum -= a[left];
                left++;
            }

            best = Math.max(best, right - left + 1);
        }
        System.out.println(best);
        
        datain.close();
    }
}
