import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CountInversion {
    public static long[] b;

    public static long solve(long[] a, int low, int high) {
        if (low >= high) return 0;

        int mid = (low + high) / 2;
        long ansLeft = solve(a, low, mid);
        long ansRight = solve(a, mid + 1, high);
        
        long ansCross = 0;

        for (int j = mid + 1; j <= high; j++) {
            long target = a[j] * a[j];
            
            int l = low;
            int r = mid;
            int firstGreater = mid + 1;
            
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (a[m] > target) {
                    firstGreater = m;
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            ansCross += (mid - firstGreater + 1);
        }

        int left = low, right = mid + 1, itr = low;

        while (left <= mid && right <= high) {
            if (a[left] <= a[right]) {
                b[itr] = a[left];
                ++itr;
                ++left;
            } else {
                b[itr] = a[right];
                ++itr;
                ++right;
            }
        }

        while (left <= mid) {
            b[itr] = a[left];
            ++itr;
            ++left;
        }

        while (right <= high) {
            b[itr] = a[right];
            ++itr;
            ++right;
        }

        for (int i = low; i <= high; i++) {
            a[i] = b[i];
        }

        return ansLeft + ansRight + ansCross;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        String line = data.readLine();
        if (line == null) return;
        
        StringTokenizer st = new StringTokenizer(line);
        int n = Integer.parseInt(st.nextToken());
        
        long[] a = new long[n];
        b = new long[n];
        
        st = new StringTokenizer(data.readLine());
        for (int i = 0; i < n; ++i) {
            a[i] = Long.parseLong(st.nextToken());
        }

        long ans = solve(a, 0, n - 1);
        
        out.println(ans);
        out.flush();
    }
}
