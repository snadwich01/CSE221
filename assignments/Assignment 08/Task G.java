import java.io.*;
import java.util.*;

public class deadlines {

    static void mergeSort(int[][] array) {
        int length = array.length;
        if (length <= 1) return;

        int middle = length / 2;
        int[][] leftArray  = new int[middle][2];
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
        int leftSize  = array.length / 2;
        int rightSize = array.length - leftSize;
        int i = 0, l = 0, r = 0;

        while (l < leftSize && r < rightSize) {
            if (leftArray[l][0] <= rightArray[r][0]) {
                array[i++] = leftArray[l++];
            } else {
                array[i++] = rightArray[r++];
            }
        }
        while (l < leftSize) {
            array[i++] = leftArray[l++];
        }
        while (r < rightSize) {
            array[i++] = rightArray[r++];
        }
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

        long reward = 0;
        long time = 0;
        for (int i = 0; i < n; i++) {
            time += tasks[i][0];
            reward += tasks[i][1] - time;
        }

        out.println(reward);
        out.flush();
        data.close();
    }
}
