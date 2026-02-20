import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader datain = new BufferedReader(new InputStreamReader(System.in));
        
        String ns = datain.readLine();

        if (ns == null){
            return;
        }
        
        StringTokenizer token1 = new StringTokenizer(ns);
        int N = Integer.parseInt(token1.nextToken());
        int S = Integer.parseInt(token1.nextToken());

        int[] arr = new int[N];
        StringTokenizer token2 = new StringTokenizer(datain.readLine());
        for (int k = 0; k < N; k++) {
            arr[k] = Integer.parseInt(token2.nextToken());
        }

        int i = 0;
        int j = N - 1;
        boolean found = false;

        while (i < j) {
            int sum = arr[i] + arr[j];

            if (sum < S) {
                i += 1;
            } else if (sum == S) {
                System.out.println((i + 1) + " " + (j + 1));
                found = true;
                break;
            } else {
                j -= 1;
            }
        }

        if (!found) {
            System.out.println("-1");
        }

        datain.close();
    }
}
