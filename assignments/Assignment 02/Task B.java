import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader datain = new BufferedReader(new InputStreamReader(System.in));
        
        String firstLine = datain.readLine();
        if (firstLine == null){
            return;
        }
        
        StringTokenizer parser1 = new StringTokenizer(firstLine);
        int N = Integer.parseInt(parser1.nextToken());
        int M = Integer.parseInt(parser1.nextToken());
        int k = Integer.parseInt(parser1.nextToken());

        int[] arrA = new int[N];
        StringTokenizer parser2 = new StringTokenizer(datain.readLine());
        for (int i = 0; i < N; i++) {
            arrA[i] = Integer.parseInt(parser2.nextToken());
        }

        int[] arrB = new int[M];
        StringTokenizer parser3 = new StringTokenizer(datain.readLine());
        for (int i = 0; i < M; i++) {
            arrB[i] = Integer.parseInt(parser3.nextToken());
        }

        int i = 0, j = M - 1;
        int scoreA = 0, scoreB = M - 1;
        
        int ini = arrA[0] + arrB[M - 1];
        int gap;

        if (ini > k) {
            gap = ini - k;
        } else {
            gap = k - ini;
        }

        while (i < N && j >= 0) {
            int currs = arrA[i] + arrB[j];
            
            int currg;
            if (currs > k) {
                currg = currs - k;
            } else {
                currg = k - currs;
            }

            if (currg < gap) {
                gap = currg;
                scoreA = i;
                scoreB = j;
            }

            if (currs < k) {
                i += 1;
            } else if (currs > k) {
                j -= 1;
            } else {
                break;
            }
        }

        System.out.println((scoreA + 1) + " " + (scoreB + 1));

        datain.close();
    }
}
