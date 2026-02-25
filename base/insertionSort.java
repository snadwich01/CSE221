public class insert{

    // O(n^2)
    // O(n) best case scenario
    public static void main(String[] args) {
        int array[] = {9, 1, 8, 7, 2, 3, 6, 5, 4};
        for(int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();

        insertionSort(array);

        for ( int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    public static void insertionSort(int[] array) {
        for (int i =0; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;

            while(j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = temp;
        }
    }
}
