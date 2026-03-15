import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class adjMat {
    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = null;

        String line = data.readLine();
        
        if (line == null) return;

        st = new StringTokenizer(line);
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] matrix = new int[n][n];

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(data.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            matrix[u - 1][v - 1] = w;
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                out.print(matrix[i][j] + " ");
            }
            out.print("\n");
        }

        out.flush();
        data.close();
    }
}
