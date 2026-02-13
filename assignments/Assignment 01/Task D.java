import java.util.Scanner;

public class isSorted {
    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);

        int T = data.nextInt();

        for (int i = 0; i < T; i++) {
            int N = data.nextInt();
            int[] arr = new int[N];

            for (int j = 0; j < N; j++) {
                arr[j] = data.nextInt();
            }

            boolean sorted = true;

            for (int j = 0; j < N - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    sorted = false;
                    break;
                }
            }

            if (sorted) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

        data.close();
    }
}
