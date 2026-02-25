public class bubble{
    // O(n^2)
    // O(n) best case scenario
    // stable and in place
    // number of swaps -> 0 -> O(n^2) worst case
    // suitable for small arrays, not for large ones

    public static void main(String[] args) {
        int array[] = {9, 1, 8, 7, 2, 3, 6, 5, 4};
        for(int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();

        bubbleSort(array);

        for ( int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    public static void bubbleSort(int[] array) {
        for(int i = 0; i < array.length - 1; i++) {
            for(int j = 0; j < array.length - i - 1; j++) {
                if(array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
