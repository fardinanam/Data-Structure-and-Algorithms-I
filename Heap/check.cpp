#include<iostream>
#include<vector>
#include "heap.h"
using namespace std;

int main()
{
    Heap h(10);
    int arr[] = {10, 5, 7, 20, 2, 15, 9, 7, 80, 1};
    vector<int> v;
    for (int i = 0; i < 10; i++)
    {
        v.push_back(arr[i]);
        h.insert(arr[i]);
    }

    for (int i = 0; i < 10; i++)
    {
        h.print();
        cout << "Deleting " << h.deleteKey() << endl;
    }
    h.print();

    heapsort(v);
    for (int i = 0; i < v.size(); i++)
        cout << v[i] << " ";
}