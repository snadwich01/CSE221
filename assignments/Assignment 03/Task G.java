import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class trees220 {
    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String line = data.readLine();
        if (line == null) return;
        int node = Integer.parseInt(line);

        int[] in = new int[node];
        int[] pre = new int[node];

        StringTokenizer t1 = new StringTokenizer(data.readLine());
        for (int i = 0; i < node; i++){
            in[i] = Integer.parseInt(t1.nextToken());
        }

        StringTokenizer t2 = new StringTokenizer(data.readLine());
        for (int i = 0; i < node; i++){
            pre[i] = Integer.parseInt(t2.nextToken());
        }

        int[] idx = {0};
        
        genpost(in, pre, 0, node - 1, idx, out);
        out.println();

        out.flush();
        data.close();
    }

    public static void genpost(int[] in, int[] pre, int inStart, int inEnd, int[] idx, PrintWriter out) {
        if (inStart > inEnd) return;

        int root = pre[idx[0]];
        idx[0]++;
        
        int rootpos = inStart;
        for (int i = inStart; i <= inEnd; i++) {
            if (in[i] == root) {
                rootpos = i;
                break;
            }
        }

        genpost(in, pre, inStart, rootpos - 1, idx, out);
        genpost(in, pre, rootpos + 1, inEnd, idx, out);

        out.print(root + " ");
    }
}
