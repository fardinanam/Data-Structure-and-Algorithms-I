#include<iostream>

using namespace std;

void swap(int[] array, int i, int j)
{
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
}

int partition(int[] array, int startIdx, int endIdx)
{
    int pivotValue = array[endIdx];

    int smallerThanPivotIdx = startIdx - 1;

    for (int greaterThanPivotIdx = startIdx; greaterThanPivotIdx < endIdx; greaterThanPivotIdx++)
    {
        if (array[greaterThanPivotIdx] <= pivotValue)
        {
            smallerThanPivotIdx++;
            swap(array, smallerThanPivotIdx, greaterThanPivotIdx);
        }
    }

    smallerThanPivotIdx++;
    swap(array, smallerThanPivotIdx, endIdx);
    return smallerThanPivotIdx;
}

void quickSort(int[] array, int startIdx, int endIdx)
{
    if (startIdx < endIdx)
    {
        int pivotIdx = partition(array, startIdx, endIdx);
        quickSort(array, pivotIdx + 1, endIdx);
        quickSort(array, startIdx, pivotIdx - 1);
    }
}

int main() 
{
    int[] array = {4, 234, 52, 52, 7, 74, 0};
    quickSort(array, 0, array.length - 1);
    cout << array << "/n";
}