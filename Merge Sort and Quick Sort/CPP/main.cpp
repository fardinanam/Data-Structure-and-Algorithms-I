#include <iostream>
#include <cstdlib>
#include <ctime>
#include "quicksort.h"
#include "mergesort.h"

using namespace std;

void generateArrayOfRandomOrder(int arr1[], int arr2[], int n)
{
    srand(time(0));

    for(int i = 0; i < n; i++) 
    {
        int element = rand();
        arr1[i] = arr2[i] = element;
    }
}

void generateArrayInAscendingOrder(int arr1[], int arr2[], int n)
{
    srand(time(0));
    int previousValue;

    for (int i = 0; i < n; i++)
    {
        int element = rand();

        if(i != 0) 
        {
            if(element < previousValue)
            {
                element += previousValue;
            }
        }
        
        arr1[i] = arr2[i] = element;
        previousValue = element;
    }
}

void generateArrayInDescendingOrder(int arr1[], int arr2[], int n) 
{
    srand(time(0));
    int previousValue;

    for (int i = 0; i < n; i++)
    {
        int element;

        if (i == 0)
        {
            element = rand();
        }
        else
        {
            element = rand() % previousValue;
        }

        arr1[i] = arr2[i] = element;
        previousValue = element;
    }
}

int main()
{
    int n, order, *arr1, *arr2;

    cout << "Number of elements of randomly generated array: ";
    cin >> n;
    arr1 = new int[n];
    arr2 = new int[n];

    cout << "Order of the array:\n";
    cout << "1. Random Order\n";
    cout << "2. Ascending Order\n";
    cout << "3. Descending Order\n";
    cin >> order;

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

    quickSort(arr1, 0, n - 1);
    mergeSort(arr2, 0, n - 1);
    for (int i = 0; i < n; i++)
    {
        cout << arr1[i];

        if (i < n - 1)
        {
            cout << ", ";
        }
        else
        {
            cout << "\n";
        }
    }

    for (int i = 0; i < n; i++)
    {
        cout << arr2[i];

        if (i < n - 1)
        {
            cout << ", ";
        }
        else
        {
            cout << "\n";
        }
    }

    delete arr1;
    delete arr2;
}