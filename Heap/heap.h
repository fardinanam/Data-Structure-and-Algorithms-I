#include<iostream>
#include<vector>

template<typename T>
void heapsort(vector<T> &v);

template <typename T>
class Heap {
    private:
        int length;
        int heapSize;
        T *container;

        void swap(int i, int j) {
            T temp = container[i - 1];
            container[i - 1] = container[j - 1];
            container[j - 1] = temp;
        }

        int parent(int i)
        {
            return i / 2;
        }

        int left(int i)
        {
            if (heapSize < i * 2)
                return -1;

            return i * 2;
        }

        int right(int i)
        {
            if (heapSize < i * 2 + 1)
                return -1;

            return i * 2 + 1;
        }

    public:
        Heap(int capacity) {
            this->container = new T[capacity];
            this->heapSize = 0;
            this->length = 0;
        }

        T getMax() {
            if(heapSize < 1) {
                throw "Heap underflow";
            }

            return container[0];
        }

        int size() {
            return heapSize;
        }

        void maxHeapify(int i) {
            if(i > heapSize) {
                return;
            }

            int l = left(i);
            int r = right(i);
            int largest;

            if(l != -1 && container[l - 1] > container[i - 1]) {
                largest = l;
            } else {
                largest = i;
            }

            if (r != -1 && container[r - 1] > container[largest - 1])
            {
                largest = r;
            }

            if(largest != i) {
                swap(i, largest);
                maxHeapify(largest);
            }
        }

        void buildMaxHeap() {
            // Max heapify function is called from the half way of the array
            for(int i = this->length / 2; i >= 1; i--) {
                maxHeapify(i);
            }

            this->heapSize = this->length;
        }

        void heapIncreaseKey(int i, T key) {
            if(key < container[i - 1]) {
                std::cout << "New key is smaller than current key\n";
                return;
            }

            container[i - 1] = key;
            int parentOfI = parent(i);

            // If the parent is smaller then exchange it and again do the same for the parent
            while(i > 1 && container[parentOfI - 1] < container[i - 1]) {
                swap(i, parentOfI);
                i = parentOfI;
                parentOfI = parent(i);
            }
        }

        void insert(T key) {
            // The new key is inserted at the end and then gradually
            // lifted it to where it should be by calling heapIncreaseKey
            length++;
            heapSize++;
            container[heapSize - 1] = key;
            heapIncreaseKey(heapSize, key);
        }

        int deleteKey() {
            if(heapSize < 1) {
                // Heap is empty
                std::cout << "Heap underflow\n";
                return -1;
            }

            T max = container[0];
            container[0] = container[--heapSize];
            length--;
            maxHeapify(1);

            return max;
        }
        void print() {
            for(int i=0; i<heapSize; i++) {
                cout<< container[i] << " ";
            }

            std::cout << std::endl;
        }

        // heapsort method is made a friend function to access the private variables heapSize and container[]
        friend void heapsort<T>(vector<T> &v);
};

template<typename T>
void heapsort(vector<T> &v) {
    Heap<T> toBeSorted(v.size());

    // Inserting the elements of the vector to the heap
    for(int i=0; i<v.size(); i++) {
        toBeSorted.insert(v[i]);
    }

    // Clearing the vector so that we can push_back the sorted values later
    v.clear();

    // Applying Heapsort algorithm and pushing the max values to the vector
    for(int i = toBeSorted.heapSize; i >= 2; i--) {
        v.push_back(toBeSorted.container[0]);
        toBeSorted.swap(i, 1);
        toBeSorted.heapSize--;
        toBeSorted.maxHeapify(1);
    }

    // The last element was not pushed in the vector in the loop
    v.push_back(toBeSorted.container[0]);
}

