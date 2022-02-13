import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Random rand = new Random();
    private static final int MAX_INT = 2147483647;

    private static void generateArrayOfRandomOrder(int arr1[], int arr2[], int n)
    {
        for(int i = 0; i < n; i++)
        {
            int element = rand.nextInt();
            arr1[i] = arr2[i] = element;
        }
    }

    private static void generateArrayInAscendingOrder(int arr1[], int arr2[], int n)
    {
        int previousValue = rand.nextInt(MAX_INT);
        arr1[0] = arr2[0] = previousValue;

        int bound = (MAX_INT - previousValue) / n;

        for (int i = 1; i < n; i++)
        {
            int element = rand.nextInt(bound) + previousValue;

            arr1[i] = arr2[i] = element;
            previousValue = element;
        }
    }

    private static void generateArrayInDescendingOrder(int arr1[], int arr2[], int n)
    {
        int previousValue = rand.nextInt(MAX_INT);
        arr1[0] = arr2[0] = previousValue;

        int bound = (MAX_INT - previousValue) / n;

        for (int i = 1; i < n; i++)
        {
            int element = previousValue - rand.nextInt(bound);

            arr1[i] = arr2[i] = element;
            previousValue = element;
        }
    }

    public static void main(String[] args) {
        int n, order;
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Number of elements of randomly generated array: ");
            n = scanner.nextInt();

            int[] arr1 = new int[n];
            int[] arr2 = new int[n];

            System.out.println("Order of the array:");
            System.out.println("1. Random Order");
            System.out.println("2. Ascending Order");
            System.out.println("3. Descending Order ");

            order = scanner.nextInt();

            switch (order)
            {
                case 1:
                    generateArrayOfRandomOrder(arr1, arr2, n);
                    break;
                case 2:
                    generateArrayInAscendingOrder(arr1, arr2, n);
                    break;
                case 3:
                    generateArrayInDescendingOrder(arr1, arr2, n);
                    break;
                default :
                    break;
            }
            long startTime = System.nanoTime();
            Sort.mergeSort(arr2, 0, n - 1);
            long endTime = System.nanoTime();
            System.out.println("MergeSort takes " + (endTime - startTime) / 1e9 + " seconds");

            startTime = System.nanoTime();
            Sort.quickSort(arr1, 0, n - 1);
            endTime = System.nanoTime();
            System.out.println("QuickSort takes " + (endTime - startTime) / 1e9 + " seconds");

            System.out.println("Sorted Arrays:");
            System.out.println("Merge Sort\tQuick Sort");

            for(int i = 0; i < n; i++) {
                System.out.println(arr2[i] + "\t" + arr1[i]);
            }
        }
    }

//    public static void main(String[] args) {
//        try {
//            FileWriter fw = new FileWriter("SortingTimeComparison.txt");
//
//            for(int n = 10; n <= 1e6; n *= 10) {
//                int[] quick = new int[n];
//                int[] merge = new int[n];
//
//                System.out.println("For n = " + n + ":");
//                fw.write("For n = " + n + ":\n");
//
//                String[] order = {"Random", "Ascending", "Descending"};
//
//                for(int o = 0; o < 3; o++) {
//                    System.out.println("Average running time for " + order[o] + " order: ");
//                    fw.write("Average running time for " + order[o] + " order: \n");
//
//                    int i = 1;
//                    double quickSortTotalTime = 0;
//                    double mergeSortTotalTime = 0;
//
//                    for(; i <= 10; i++) {
//                        switch (o) {
//                            case 0:
//                                generateArrayOfRandomOrder(quick, merge, n);
//                                break;
//                            case 1:
//                                generateArrayInAscendingOrder(quick, merge, n);
//                                break;
//                            case 2:
//                                generateArrayInDescendingOrder(quick, merge, n);
//                                break;
//                        }
//
//
//                        long startTime = System.nanoTime();
//                        Sort.mergeSort(merge, 0, n - 1);
//                        long endTime = System.nanoTime();
//                        mergeSortTotalTime += (endTime - startTime) / 1e9;
//
//                        startTime = System.nanoTime();
//                        Sort.quickSort(quick, 0, n - 1);
//                        endTime = System.nanoTime();
//                        quickSortTotalTime += (endTime - startTime) / 1e9;
//                    }
//
//                    System.out.println("Merge Sort: " + mergeSortTotalTime / i + " seconds");
//                    fw.write("Merge Sort: " + mergeSortTotalTime / i + " seconds\n");
//                    System.out.println("Quick Sort: " + quickSortTotalTime / i + " seconds");
//                    fw.write("Quick Sort: " + quickSortTotalTime / i + " seconds\n");
//
//                    System.out.println();
//                    fw.write("\n");
//                }
//            }
//
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
