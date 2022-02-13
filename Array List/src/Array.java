import java.util.Arrays;

public class Array {
    private int length;
    private int size;
    private String[] arr;

    public Array(int n) {
        length = 0;
        size = n;
        arr = new String[length];
    }


    public Array() {
        this(16); // Let the predefined size be 16
    }

    public Array(String[] A) {
        length = A.length;
        size = length * 2;
        arr = new String[size];
        for(int i=0; i<length; i++) {
            arr[i] = A[i];
        }
    }

    public String[] getArray() {
        String[] temp = new String[length];
        for(int i=0; i<length; i++) {
            temp[i] = arr[i];
        }
        return temp;
    }

    public String getAnElement(int i) throws IndexOutOfBoundsException{
        if(i < 0 || i >= length) {
            throw new IndexOutOfBoundsException();
        }
        return arr[i];
    }

    private void increaseSize() {
        String[] temp = new String[size * 2];
        for(int i=0; i<length; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
        size *= 2;
    }

    public void add(String element) {
        if(size == length) {
            increaseSize();
        }
        arr[length] = element;
        length++;
    }

    public void add(int i, String element) throws IndexOutOfBoundsException{
        if(i < 0 || i > length) {
            throw new IndexOutOfBoundsException();
        }
        if(size == length) {
            increaseSize();
        }
        for(int j = length; j >= i; j--) {
            if(j == i) {
                arr[j] = element;
            } else {
                arr[j] = arr[j-1];
            }
        }
        length++;
    }

    public void remove(String element) {
        int count = 0;
        String[] temp = new String[size];
        int tempI = 0; // To keep the track of temp's last index
        for(int arrI=0; arrI<length; arrI++) {
            if(!arr[arrI].equals(element)) {
                temp[tempI++] = arr[arrI];
            } else {
                count++;
            }
        }
        length -= count;
        arr = temp;
    }

    public int[] findIndex(String element) {
        int count = 0;
        for(int i = 0; i<length; i++) {
            if(element.equals(arr[i])) {
                count++;
            }
        }
        int[] indices = new int[count];
        int indicesI = 0;
        for(int i=0; i<length; i++) {
            if(element.equals(arr[i])) {
                indices[indicesI++] = i;
            }
        }
        return indices;
    }

    public Array subArray(int start, int end) throws IndexOutOfBoundsException{
        if(start < 0 || end >= length) {
            throw new IndexOutOfBoundsException();
        }
        String[] subArr = new String[end - start + 1];
        for(int i = 0; i <= end - start; i++) {
            subArr[i] = arr[start + i];
        }
        return new Array(subArr);
    }

    public void merge(String[] A1, String[] A2) {
        int A1Length = A1.length;
        int A2Length = A2.length;
        int newLength = A1Length + A2Length;
        String[] temp = new String[newLength];

        int i = 0, j = 0, k = 0;
        while(i < A1Length && j < A2Length) {
            if(A1[i].compareTo(A2[j]) < 0) {
                temp[k++] = A1[i++];
            } else {
                temp[k++] = A2[j++];
            }
        }
        if(i == A1Length) {
            while(j < A2Length) {
                temp[k++] = A2[j++];
            }
        } else if(j == A2Length){
            while(i < A1Length) {
                temp[k++] = A1[i++];
            }
        }

        length = newLength;
        size = newLength;
        arr = temp;
    }

    public int length() {
        return length;
    }

    public boolean isEmpty() {
        if(length == 0)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return Arrays.toString(getArray());
    }

    public static void main(String[] args) {
        String[] arr1 = {"This", "is", "the", "first", "array"};
        String[] A1 = {"a", "b", "d", "e", "g", "i"};
        String[] A2 = {"a", "b", "c", "f"};

        Array array1 = new Array(arr1);
        System.out.println("Initial value of array1: " + array1);
        System.out.println("Element at index 3: " + array1.getAnElement(3));

        System.out.println("\nAdding a string at the end:");
        array1.add("This");
        System.out.println("array1 after adding 'This': " + array1);

        System.out.println("\nAdding a string at the middle:");
        array1.add(1, "This");
        System.out.println("array1 after adding 'This': " + array1);

        System.out.println("\nChecking findIndex(): Indices of 'This' in array1: " + Arrays.toString(array1.findIndex("This")));

        System.out.println("\nChecking array.remove()");
        array1.remove("This");
        System.out.println("array1 after removing 'This': " + array1);

        System.out.println("\nDemonstrating merge between two arrays:" +
                Arrays.toString(A1) + " and " + Arrays.toString(A2));
        Array array = new Array();
        array.merge(A1, A2);
        System.out.println(array);

        System.out.println("Demonstrating subArray()");
        System.out.println("Sub array of array in the indices 2,5 is: " + array.subArray(2,5));
    }
}
