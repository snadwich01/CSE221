import java.io.*;
import java.util.StringTokenizer;

public class arithmetic {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {

            StringTokenizer token = new StringTokenizer(br.readLine());

            token.nextToken();

            double first = Double.parseDouble(token.nextToken());
            String op = token.nextToken();
            double second = Double.parseDouble(token.nextToken());

            double res = 0;

            if(op.equals("+")) {
                res = first + second;
            } else if(op.equals("-")) {
                res = first - second;
            } else if(op.equals("*")) {
                res = first * second;
            } else if(op.equals("/")) {
                res = first / second;
            }

            System.out.printf("%.6f\n", res);
        }

        out.close();
    }
}
