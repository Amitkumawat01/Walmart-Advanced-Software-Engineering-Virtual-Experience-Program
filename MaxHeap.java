import java.util.Arrays;
import java.util.Scanner;

public class MaxHeap {
    // To store array of elements in heap
    private int[] heapArray;
     
    private int children;
    // max size of the heap
    private int heap_capacity;
     
    // Current number of elements in the heap
    private int current_heap_size;
 
    // Constructor
    public MaxHeap(int Heap_capacity, int Children_exponent) {
        heap_capacity = Heap_capacity;
        children = (int)Math.pow(2,Children_exponent);
        heapArray = new int[heap_capacity];
        current_heap_size = 0;
    }

    // Swapping using reference
    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    // Get the Parent index for the given child index
    private int parent(int child_ind) {
        return (child_ind - 1) / children;
    }
     
    // Get the ith Child [1-based index] index for the given parent index
    private int ith_child(int parent, int i) {
        return children * parent + i;
    }

    // Inserts a new key int MaxHeap
    public boolean insert(int key) {
        if (current_heap_size == heap_capacity) {
            // heap is full
            return false;
        }
     
        // First insert the new key at the end
        int i = current_heap_size;
        heapArray[i] = key;
        current_heap_size++;
         
        // Fix the min heap property if it is violated
        while (i != 0 && heapArray[i] > heapArray[parent(i)]) {
            swap(heapArray, i, parent(i));
            i = parent(i);
        }
        return true;
    }

    // Heapity fn
    private void MaxHeapify(int key) {
        int greatest_child = key;

        for (int j = 1; j <= children; j++) {
            int child_ind = ith_child(key, j);
            if (child_ind >= current_heap_size) {
                // No more children to compare, break the loop
                break;
            }
            if (heapArray[child_ind] > heapArray[greatest_child]) {
                greatest_child = child_ind;
            }
        }
 
        if (greatest_child != key) {
            swap(heapArray, key, greatest_child);
            MaxHeapify(greatest_child);
        }
    }

    public int get_max() {
        return heapArray[0];
    }
    public int pop_max() {
        if (current_heap_size <= 0) {
            return Integer.MAX_VALUE;
        }
 
        if (current_heap_size == 1) {
            current_heap_size--;
            return heapArray[0];
        }
         
        // Store the max value,
        // and remove it from heap
        int root = heapArray[0];
 
        heapArray[0] = heapArray[current_heap_size - 1];
        current_heap_size--;
        MaxHeapify(0);
 
        return root;
    }
    
    
    public static void main(String[] args) throws Exception {
        MaxHeap maxHeap = new MaxHeap(100000,10);

        int n=10;
        for(int i=0; i<n; i++){
            maxHeap.insert((int)(Math.random()*1000000));
        }
        
        while(n>0){
            System.out.println(maxHeap.get_max());
            maxHeap.pop_max();
            n--;
        }
    }
}
