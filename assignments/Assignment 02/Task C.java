import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader datain = new BufferedReader(new InputStreamReader(System.in));
        
        String nx = datain.readLine();
        if (nx == null) return;
        
        StringTokenizer token1 = new StringTokenizer(nx);
        int n = Integer.parseInt(token1.nextToken());
        int x = Integer.parseInt(token1.nextToken());

        int[] arr = new int[n];
        int[] pos = new int[n];
        
        StringTokenizer token2 = new StringTokenizer(datain.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(token2.nextToken());
            pos[i] = i + 1;
        }

        //sorting
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int tempv = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tempv;
                    
                    int tempp = pos[i];
                    pos[i] = pos[i + 1];
                    pos[i + 1] = tempp;
                    
                    swapped = true;
                }
            }
        }

        boolean found = false;

        for (int i = 0; i < n; i++) {
            int rem = x - arr[i];
            
            int j = i + 1;
            int k = n - 1;
            
            while (j < k) {
                int sum = arr[j] + arr[k];

                if (sum < rem) {
                    j += 1;
                } else if (sum > rem) {
                    k -= 1;
                } else {
                    System.out.println(pos[i] + " " + pos[j] + " " + pos[k]);
                    found = true;
                    break;
                }
            }
            if (found) break;
        }

        if (!found) {
            System.out.println("-1");
        }

        datain.close();
    }
}
