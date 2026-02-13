import java.util.Scanner;

public class revSort {

    public static void main(String[] args) {

        Scanner data = new Scanner(System.in);
        int N = data.nextInt();

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = data.nextInt();
        }

        int[] sorted = arr.clone();
        sortArray(sorted);

        int counteven = (N + 1) / 2;
        int countodd = N / 2;

        int[] orgeven = new int[counteven];
        int[] orgodd = new int[countodd];
        int[] sorteven = new int[counteven];
        int[] sortodd = new int[countodd];

        int even = 0, odd = 0;
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                orgeven[even] = arr[i];
                sorteven[even] = sorted[i];
                even++;
            } else {
                orgodd[odd] = arr[i];
                sortodd[odd] = sorted[i];
                odd++;
            }
        }

        sortArray(orgeven);
        sortArray(orgodd);
        sortArray(sorteven);
        sortArray(sortodd);

        for (int i = 0; i < counteven; i++) {
            if (orgeven[i] != sorteven[i]) {
                System.out.println("NO");
                data.close();
                return;
            }
        }

        for (int i = 0; i < countodd; i++) {
            if (orgodd[i] != sortodd[i]) {
                System.out.println("NO");
                data.close();
                return;
            }
        }

        int[][] moves = new int[2000000][2];
        int count = 0;

        for (int i = 0; i <= N - 3; i++) {

            int minPos = i;
            for (int j = i; j < N; j++) {
                if (arr[j] < arr[minPos]) {
                    minPos = j;
                }
            }

            int distance = minPos - i;
            int steps = distance / 2;

            for (int s = 0; s < steps; s++) {

                int pos = minPos - 2;

                int temp = arr[pos];
                arr[pos] = arr[pos + 2];
                arr[pos + 2] = temp;

                moves[count][0] = pos + 1;
                moves[count][1] = pos + 3;
                count++;

                minPos -= 2;
            }

            if (minPos - i == 1) {

                for (int k = 0; k < 2; k++) {

                    int temp = arr[i];
                    arr[i] = arr[i + 2];
                    arr[i + 2] = temp;

                    moves[count][0] = i + 1;
                    moves[count][1] = i + 3;
                    count++;
                }
            }
        }

        for (int i = 0; i < N - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                System.out.println("NO");
                data.close();
                return;
            }
        }

        //--------------------------------
        System.out.println("YES");
        System.out.println(count);
        for (int i = 0; i < count; i++) {
            System.out.println(moves[i][0] + " " + moves[i][1]);
        }

        data.close();
    }

    public static void sortArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minopos = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minopos]) {
                    minopos = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minopos];
            arr[minopos] = temp;
        }
    }
}
