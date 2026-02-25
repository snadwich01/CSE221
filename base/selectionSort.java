public class select{
    // O(n^2)
    // O(n^2) best case scenario
    // not stable but in place
    // number of swaps -> 0 -> n-1 worst case
    // suitable for small arrays, not for large ones
    // requires minimum number of swaps for worst case

    public static void main(String[] args) {
        int array[] = {9, 1, 8, 7, 2, 3, 6, 5, 4};
        for(int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();

        selectionSort(array);

        for ( int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    public static void selectionSort(int[] array) {
        for(int i = 0; i < array.length - 1; i++) {
            int min = i;
            for(int j = i + 1; j < array.length; j++) {
                if(array[min] > array[j]) {
                    min = j;
                }
            }

            int temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
    }
}
