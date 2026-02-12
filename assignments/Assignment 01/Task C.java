import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);
        int T = data.nextInt();
        
        for (int i = 0; i < T; i++) {
            int N = data.nextInt();
            
            long sum = (long) N * (N + 1) / 2;
            
            System.out.println(sum);
        }

        data.close();
    }
}
