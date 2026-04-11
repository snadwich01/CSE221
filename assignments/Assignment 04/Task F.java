import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class kingMove {
    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = null;

        String line = data.readLine();

        if (line == null) return;
        
        int n = Integer.parseInt(line.trim());

        st = new StringTokenizer(data.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int[][] pos = new int[8][2];
        int size = 0;

        for (int rowdir = -1; rowdir <= 1; rowdir++) {
            for (int coldir = -1; coldir <= 1; coldir++) {
                if (!(rowdir == 0 && coldir == 0)) {
                    int nx = x + rowdir;
                    int ny = y + coldir;
                    if (nx >= 1 && nx <= n && ny >= 1 && ny <= n) {
                        pos[size][0] = nx;
                        pos[size][1] = ny;
                        size++;
                    }
                }
            }
        }

        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (pos[i][0] > pos[j][0] || (pos[i][0] == pos[j][0] && pos[i][1] > pos[j][1])) {
                    int tempx = pos[i][0], tempy = pos[i][1];
                    pos[i][0] = pos[j][0];
                    pos[i][1] = pos[j][1];
                    pos[j][0] = tempx;
                    pos[j][1] = tempy;
                }
            }
        }

        out.println(size);
        for (int i = 0; i < size; i++) {
            out.println(pos[i][0] + " " + pos[i][1]);
        }

        out.flush();
        data.close();
    }
}
