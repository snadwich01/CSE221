import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class edgequery {
    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = null;

        String line = data.readLine();

        if (line == null) return;

        st = new StringTokenizer(line);
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] start = new int[m], dest = new int[m];
        int[] indeg = new int[n + 1], outdeg = new int[n + 1];

        st = new StringTokenizer(data.readLine());
        for (int i = 0; i < m; i++) start[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(data.readLine());
        for (int i = 0; i < m; i++) dest[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            outdeg[start[i]]++;
            indeg[dest[i]]++;
        }

        for (int i = 1; i <= n; i++) {
            if (i > 1) {
                out.print(" ");
            }
            
            out.print(indeg[i] - outdeg[i]);
        }
        out.println();

        out.flush();
        data.close();
    }
}
