import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class inversion {

    static long mergeSort(int[] arr, int[] temp, int left, int right) {
        long inv = 0;

        if (left < right) {
            int mid = (left + right) / 2;

            inv += mergeSort(arr, temp, left, mid);
            inv += mergeSort(arr, temp, mid + 1, right);

            int i = left, j = mid + 1, k = left;

            while (i <= mid && j <= right) {
                if (arr[i] <= arr[j]) {
                    temp[k++] = arr[i++];
                } else {
                    temp[k++] = arr[j++];
                    inv += (mid - i + 1);
                }
            }

            while (i <= mid) {
                temp[k++] = arr[i++];
            }
            while (j <= right) {
                temp[k++] = arr[j++];
            }

            for (i = left; i <= right; i++) {
                arr[i] = temp[i];
            }
        }

        return inv;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader datain = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(datain.readLine());
        int[] arr = new int[n];
        int[] temp = new int[n];

        StringTokenizer st = new StringTokenizer(datain.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long inv = mergeSort(arr, temp, 0, n - 1);

        out.println(inv);

        for (int x : arr) {
            out.print(x + " ");
        }

        out.flush();
        datain.close();
    }
}
