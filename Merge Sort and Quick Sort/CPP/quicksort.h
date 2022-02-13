#include<cstdlib>
#include<ctime>

void swap(int arr[], int i, int j)
{
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}

int partition(int arr[], int startIdx, int endIdx)
{
    int pivotValue = arr[endIdx];

    int smallerThanPivotIdx = startIdx - 1;

    for (int greaterThanPivotIdx = startIdx; greaterThanPivotIdx < endIdx; greaterThanPivotIdx++)
    {
        if (arr[greaterThanPivotIdx] <= pivotValue)
        {
            smallerThanPivotIdx++;
            swap(arr, smallerThanPivotIdx, greaterThanPivotIdx);
        }
    }

    int pivotIdx = smallerThanPivotIdx + 1;
    swap(arr, pivotIdx, endIdx);
    return pivotIdx;
}

int randomizedPartition(int arr[], int startIdx, int endIdx)
{
    srand(time(0));
    
    int pivotIdx = rand() % (endIdx - startIdx + 1) + startIdx;
    swap(arr, pivotIdx, endIdx);
    return partition(arr, startIdx, endIdx);
}

void quickSort(int arr[], int startIdx, int endIdx)
{
    if (startIdx < endIdx)
    {
        int pivotIdx = partition(arr, startIdx, endIdx);
        quickSort(arr, pivotIdx + 1, endIdx);
        quickSort(arr, startIdx, pivotIdx - 1);
    }
}


