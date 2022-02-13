import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Sort {
    private static final Random rand = new Random();
    private static final int INF = 2147483647;

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static int partition(int[] array, int startIdx, int endIdx) {
        int pivotValue = array[endIdx];

        int smallerThanPivotIdx = startIdx - 1;

        for(int greaterThanPivotIdx = startIdx; greaterThanPivotIdx < endIdx; greaterThanPivotIdx++) {
            if(array[greaterThanPivotIdx] <= pivotValue) {
                smallerThanPivotIdx++;
                swap(array, smallerThanPivotIdx, greaterThanPivotIdx);
            }
        }

        int pivotIdx = smallerThanPivotIdx + 1;
        swap(array, pivotIdx, endIdx);
        return pivotIdx;
    }

    private static int randomizedPartition(int[] array, int startIdx, int endIdx) {
        int pivotIdx = rand.nextInt(endIdx - startIdx + 1) + startIdx;
        swap(array, pivotIdx, endIdx);

        return partition(array, startIdx, endIdx);
    }

    public static void quickSort(int[] array, int startIdx, int endIdx) {
        if(startIdx < endIdx) {
            int pivotIdx = partition(array, startIdx, endIdx);

            quickSort(array, startIdx, pivotIdx - 1);
            quickSort(array, pivotIdx + 1, endIdx);
        }
    }

    private static void merge(int[] array, int startIdx, int midIdx, int endIdx) {
        int leftArrSize = midIdx - startIdx + 1;
        int rightArrSize = endIdx - midIdx;

        int[] leftHalf = new int[leftArrSize + 1], rightHalf = new int[rightArrSize + 1];

        for(int i = 0; i < leftArrSize; i++) {
            leftHalf[i] = array[startIdx + i];
        }

        for(int i = 0; i < rightArrSize; i++) {
            rightHalf[i] = array[midIdx + i + 1];
        }

        leftHalf[leftArrSize] = INF;
        rightHalf[rightArrSize] = INF;

        int leftArrIdx = 0, rightArrIdx = 0;

        for(int k = startIdx; k <= endIdx; k++) {
            if(leftHalf[leftArrIdx] <= rightHalf[rightArrIdx]) {
                array[k] = leftHalf[leftArrIdx];
                leftArrIdx++;
            } else {
                array[k] = rightHalf[rightArrIdx];
                rightArrIdx++;
            }
        }
    }

    public static void mergeSort(int arr[], int startIdx, int endIdx)
    {
        if(startIdx < endIdx)
        {
            int midIdx = (startIdx + endIdx) / 2;

            mergeSort(arr, startIdx, midIdx);
            mergeSort(arr, midIdx + 1, endIdx);
            merge(arr, startIdx, midIdx, endIdx);
        }
    }
}
