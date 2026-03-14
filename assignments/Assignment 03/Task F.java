import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ordering {
    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String in = data.readLine();
        if (in == null) return;
        int n = Integer.parseInt(in);

        long[] arr = new long[n];
        StringTokenizer st = new StringTokenizer(data.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        printer(arr, 0, n - 1, out);
        out.println();

        out.flush();
        data.close();
    }

    public static void printer(long[] arr, int start, int end, PrintWriter out) {
        if (start > end) {
            return;
        }

        int mid = (start + end) / 2;

        out.print(arr[mid] + " ");

        printer(arr, start, mid - 1, out);
        printer(arr, mid + 1, end, out);
    }
}
