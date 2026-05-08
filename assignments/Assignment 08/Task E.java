import java.io.*;
import java.util.StringTokenizer;

public class tasksel {

    static void mergeSort(int[][] array) {
        int length = array.length;
        if (length <= 1) return;

        int middle = length / 2;
        int[][] leftArray  = new int[middle][2];
        int[][] rightArray = new int[length - middle][2];

        int i = 0, j = 0;
        for (; i < length; i++) {
            if (i < middle) {
                leftArray[i] = array[i];
            } else {
                rightArray[j] = array[i];
                j++;
            }
        }
        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(leftArray, rightArray, array);
    }

    static void merge(int[][] leftArray, int[][] rightArray, int[][] array) {
        int leftSize  = array.length / 2;
        int rightSize = array.length - leftSize;
        int i = 0, l = 0, r = 0;

        while (l < leftSize && r < rightSize) {
            if (leftArray[l][1] <= rightArray[r][1]) {
                array[i++] = leftArray[l++];
            } else {
                array[i++] = rightArray[r++];
            }
        }
        while (l < leftSize)  array[i++] = leftArray[l++];
        while (r < rightSize) array[i++] = rightArray[r++];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader data = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st;

        int n = Integer.parseInt(data.readLine().trim());

        int[][] tasks = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(data.readLine());
            tasks[i][0] = Integer.parseInt(st.nextToken());
            tasks[i][1] = Integer.parseInt(st.nextToken());
        }

        mergeSort(tasks);

        int[][] picked = new int[n][2];
        int count = 0;
        int lastEnd = -1;

        for (int i = 0; i < n; i++) {
            if (tasks[i][0] > lastEnd) {
                picked[count++] = tasks[i];
                lastEnd = tasks[i][1];
            }
        }

        out.println(count);
        for (int i = 0; i < count; i++) {
            out.println(picked[i][0] + " " + picked[i][1]);
        }

        out.flush();
        data.close();
    }
}
