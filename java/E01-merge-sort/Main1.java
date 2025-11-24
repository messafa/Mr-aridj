public class Main1 {
    
    public static void main(String[] args) {
        int[] array = {8,4,5,3,7,9,0,3,6,1,2};

        mergeSort(array);


        for (int i=0; i<array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    private static void mergeSort(int[] array) {
        int length = array.length;
        if(length < 2) {
            return; // Base case: array is already sorted
        }

        int mid = length / 2;
        int[] left = new int[mid];
        int[] right = new int[length - mid];
        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
        }
        for (int i = mid; i < length; i++) {
            right[i - mid] = array[i];
        }
        mergeSort(left);
        mergeSort(right);
        merge(array, left, right);

        
    }

    private static void merge(int[] array, int[] left,  int[] right) {
        int leftSize = array.length / 2;
        int rightSize = array.length - leftSize;
        int i = 0, j = 0, k = 0; // i for left, j for right, k for array
        while (i < leftSize && j < rightSize) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        while (i < leftSize) {
            array[k++] = left[i++];
        }
        while (j < rightSize) {
            array[k++] = right[j++];
        }
    }


}
