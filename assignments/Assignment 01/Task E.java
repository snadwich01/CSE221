import java.util.*;

public class revSort {
    public static void main(String[] args) {

        Scanner data = new Scanner(System.in);

        int size = data.nextInt();
        int[] a = new int[size];

        for (int i = 0; i < size; i++) {
            a[i] = data.nextInt();
        }

        if (size < 3) {
            boolean chk = true;
            for (int i = 0; i + 1 < size; i++) {
                if (a[i] > a[i + 1]) {
                    chk = false;
                    break;
                }
            }

            if (chk) {
                System.out.println("YES");
                System.out.println(0);
            } else {
                System.out.println("NO");
            }
            return;
        }

        int[] moves = new int[3000000];
        int movecount = 0;

        boolean flipped = true;

        for (int i = 0; i < size && flipped; i++) {
            flipped = false;

            for (int j = 0; j + 2 < size; j++) {
                if (a[j] > a[j + 2]) {
                    int t = a[j];
                    a[j] = a[j + 2];
                    a[j + 2] = t;

                    moves[movecount] = j + 1;
                    movecount++;
                    flipped = true;
                }
            }
        }

        boolean sorted = true;
        for (int i = 1; i < size; i++) {
            if (a[i - 1] > a[i]) {
                sorted = false;
                break;
            }
        }

        if (!sorted) {
            System.out.println("NO");
            return;
        }

        System.out.println("YES");
        System.out.println(movecount);

        for (int i = 0; i < movecount; i++) {
            System.out.println(moves[i] + " " + (moves[i] + 2));
        }
    }
}
