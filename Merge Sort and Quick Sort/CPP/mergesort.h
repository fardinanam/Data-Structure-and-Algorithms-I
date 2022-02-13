#include <iostream>
#include <limits>

#define INF std::numeric_limits<int>::max();

using namespace std;

void merge(int arr[], int startIdx, int midIdx, int endIdx) 
{
    int n1 = midIdx - startIdx + 1;
    int n2 = endIdx - midIdx;

    int leftHalfArr[n1 + 1];
    int rightHalfArr[n2 + 1];

    for(int i = 0; i < n1; i++) 
    {
        leftHalfArr[i] = arr[startIdx + i];
    }

    for(int i = 0; i < n2; i++)
    {
        rightHalfArr[i] = arr[midIdx + i + 1];
    }

    leftHalfArr[n1] = INF;
    rightHalfArr[n2] = INF;
    
    int leftArrIdx = 0, rightArrIdx = 0;

    for(int i = startIdx; i <= endIdx; i++)
    {
        if(leftHalfArr[leftArrIdx] <= rightHalfArr[rightArrIdx])
        {
            arr[i] = leftHalfArr[leftArrIdx];
            leftArrIdx++;
        } 
        else
        {
            arr[i] = rightHalfArr[rightArrIdx];
            rightArrIdx++;
        }
    }
}

void mergeSort(int arr[], int startIdx, int endIdx)
{
    if(startIdx < endIdx)
    {
        int midIdx = (startIdx + endIdx) / 2;

        mergeSort(arr, startIdx, midIdx);
        mergeSort(arr, midIdx + 1, endIdx);
        merge(arr, startIdx, midIdx, endIdx);
    }
}