import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class stackodim {
    static int row, col;
    static char[][] map;
    static boolean[][] visited;
    static int[] rowD = {1, -1, 0, 0};
    static int[] colD = {0, 0, 1, -1};

    static int[] sRow = new int[1000 * 1000];
    static int[] sCol = new int[1000 * 1000];

    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st;

        st = new StringTokenizer(data.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new char[row][col];
        visited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            map[i] = data.readLine().toCharArray();
        }

        int maxDiamonds = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visited[i][j] && map[i][j] != '#') {
                    int total = dfs(i, j);
                    if (total > maxDiamonds){
                        maxDiamonds = total;
                    }
                }
            }
        }

        out.println(maxDiamonds);
        out.flush();
        data.close();
    }
    
    static int dfs(int startX, int startY) {
        int top = 0;
        int count = 0;
        visited[startX][startY] = true;
        sRow[top] = startX;
        sCol[top] = startY;
        top++;

        while (top > 0) {
            top--;
            int x = sRow[top];
            int y = sCol[top];

            if (map[x][y] == 'D') count++;

            for (int i = 0; i < 4; i++) {
                int nx = x + rowD[i];
                int ny = y + colD[i];
                if (nx >= 0 && nx < row && ny >= 0 && ny < col && !visited[nx][ny] && map[nx][ny] != '#') {
                    visited[nx][ny] = true;
                    sRow[top] = nx;
                    sCol[top] = ny;
                    top++;
                }
            }
        }
        return count;
    }
}
