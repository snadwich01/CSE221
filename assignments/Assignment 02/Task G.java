import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class countN {
    public static void main(String[] args) throws IOException {
        BufferedReader datain = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String first = datain.readLine();
        if (first == null){
            return;
        }

        StringTokenizer token1 = new StringTokenizer(first);
        int n = Integer.parseInt(token1.nextToken());
        int q = Integer.parseInt(token1.nextToken());

        int[] a = new int[n];
        StringTokenizer token2 = new StringTokenizer(datain.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(token2.nextToken());
        }

        for (int i = 0; i < q; i++) {
            String line = datain.readLine();
            if (line == null){
                break;
            }

            StringTokenizer token3 = new StringTokenizer(line);
            int x = Integer.parseInt(token3.nextToken());
            int y = Integer.parseInt(token3.nextToken());

            //bin search
            int left = 0;
            int right = n - 1;
            int start = n;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (a[mid] >= x) {
                    start = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            left = 0;
            right = n - 1;
            int end = -1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (a[mid] <= y) {
                    end = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            if (start <= end) {
                out.println(end - start + 1);
            } else {
                out.println(0);
            }
        }
        
        out.flush();
        datain.close();
    }
}
