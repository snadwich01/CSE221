import java.util.Scanner;

public class ancientAlgo {
    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);
        
        int N = data.nextInt();
        int[] arr = new int[N];
        
        for (int i = 0; i < N; i++) {
            arr[i] = data.nextInt();
        }
        
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < N - 1; i++) {
                if ((arr[i] % 2 == arr[i + 1] % 2) && (arr[i] > arr[i + 1])) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
        }
        
        for (int i = 0; i < N; i++) {
            System.out.print(arr[i]);
            if (i < N - 1){
                System.out.print(" ");
            }
        }
        System.out.println();
        
        data.close();
    }
}
