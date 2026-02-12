import java.util.Scanner;

public class arithmetic{
    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);

        int T = data.nextInt();

        for(int i = 0; i < T; i++) {
            data.next();
            double first = data.nextDouble();
            String sign = data.next();
            double second = data.nextDouble();

            double res = 0;

            if(sign.equals("+")) {
                res = first + second;
            } else if(sign.equals("-")) {
                res = first - second;
            } else if(sign.equals("*")) {
                res = first * second;
            } else if(sign.equals("/")) {
                res = first / second;
            }

            System.out.printf("%.6f\n", res);
        }

        data.close();
    }
}
