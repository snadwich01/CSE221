public class QuickSort {

    public static void quickSort(int[] A, int l, int r) {
        if (l < r) {
            int pivotIndex = partition(A, l, r);
            quickSort(A, l, pivotIndex - 1);
            quickSort(A, pivotIndex + 1, r);
        }
    }

    public static int partition(int[] A, int l, int r) {
        int pivot = A[l];   // first element as pivot
        int i = l;

        for (int j = l + 1; j <= r; j++) {
            if (A[j] <= pivot) {
                i++;
                swap(A, i, j);
            }
        }

        swap(A, l, i);
        return i;
    }

    public static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    // Optional: test
    public static void main(String[] args) {
        int[] arr = {8, 3, 5, 2, 9, 1};

        quickSort(arr, 0, arr.length - 1);

        for (int x : arr) {
            System.out.print(x + " ");
        }
    }
}
