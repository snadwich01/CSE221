import java.io.*;
import java.util.StringTokenizer;

public class taskselII {

    static void mergeSort(int[][] array) {
        int length = array.length;

        if (length <= 1) {
            return;
        }

        int middle = length / 2;

        int[][] leftArray = new int[middle][2];
        int[][] rightArray = new int[length - middle][2];

        for (int i = 0; i < middle; i++) {
            leftArray[i] = array[i];
        }

        for (int i = middle; i < length; i++) {
            rightArray[i - middle] = array[i];
        }

        mergeSort(leftArray);
        mergeSort(rightArray);

        merge(leftArray, rightArray, array);
    }

    static void merge(int[][] leftArray, int[][] rightArray, int[][] array) {

        int leftSize = leftArray.length;
        int rightSize = rightArray.length;

        int i = 0, l = 0, r = 0;

        while (l < leftSize && r < rightSize) {

            if (leftArray[l][1] <= rightArray[r][1]) {
                array[i] = leftArray[l];
                l++;
            }

            else {
                array[i] = rightArray[r];
                r++;
            }

            i++;
        }

        while (l < leftSize) {
            array[i] = leftArray[l];
            l++;
            i++;
        }

        while (r < rightSize) {
            array[i] = rightArray[r];
            r++;
            i++;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader data =
                new BufferedReader(new InputStreamReader(System.in));

        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st;

        int tests = Integer.parseInt(data.readLine().trim());

        for (int t = 0; t < tests; t++) {

            st = new StringTokenizer(data.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[][] tasks = new int[n][2];

            for (int i = 0; i < n; i++) {

                st = new StringTokenizer(data.readLine());

                tasks[i][0] = Integer.parseInt(st.nextToken());
                tasks[i][1] = Integer.parseInt(st.nextToken());
            }

            mergeSort(tasks);

            int[] freeTime = new int[m];

            for (int i = 0; i < m; i++) {
                freeTime[i] = -1;
            }

            int total = 0;

            for (int i = 0; i < n; i++) {

                int start = tasks[i][0];
                int end = tasks[i][1];

                int bestPerson = -1;
                int bestTime = Integer.MIN_VALUE;

                for (int p = 0; p < m; p++) {

                    if (freeTime[p] < start &&
                            freeTime[p] > bestTime) {

                        bestTime = freeTime[p];
                        bestPerson = p;
                    }
                }

                if (bestPerson != -1) {

                    freeTime[bestPerson] = end;
                    total++;
                }
            }

            out.println(total);
        }

        out.flush();
        data.close();
    }
}
