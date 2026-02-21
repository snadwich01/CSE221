import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class KDS {
    public static void main(String[] args) throws IOException {
        BufferedReader datain = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String first = datain.readLine();
        if (first == null){
            return;
        }
        
        StringTokenizer tok1 = new StringTokenizer(first);
        int N = Integer.parseInt(tok1.nextToken());
        int K = Integer.parseInt(tok1.nextToken());

        int[] A = new int[N];
        StringTokenizer tok2 = new StringTokenizer(datain.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(tok2.nextToken());
        }

        int[] count = new int[N + 1];
        int dist = 0;
        int max = 0;
        int left = 0;

        for (int right = 0; right < N; right++) {
            if (count[A[right]] == 0) {
                dist++;
            }
            count[A[right]]++;

            while (dist > K) {
                count[A[left]]--;
                if (count[A[left]] == 0) {
                    dist--;
                }
                left++;
            }

            max = Math.max(max, right - left + 1);
        }
        out.println(max);

        out.flush();
        datain.close();
    }
}
