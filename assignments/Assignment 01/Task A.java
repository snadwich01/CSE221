import java.util.Scanner;

public class oddeven{
    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);

        int T = data.nextInt();

        for(int i = 0; i < T; i++) {
            int N = data.nextInt();

            if(N % 2 == 0) {
                System.out.println(N + " is an Even number.");
            } else {
                System.out.println(N + " is an Odd number.");
            }
        }

        data.close();
    }
}
