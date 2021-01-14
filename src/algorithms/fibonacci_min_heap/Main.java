package algorithms.fibonacci_min_heap;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static FibonacciMinHeap<Integer> heap = new FibonacciMinHeap<>(Integer.MIN_VALUE);
    static FibonacciMinHeap<Integer> heap2 = new FibonacciMinHeap<>(Integer.MIN_VALUE);

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(7, 9, 11, 22, 0, 1));

        for (Integer element : list) {
            System.out.println("\n\nAfter inserting element " + element + " in heap 1:");
            heap.insert(element);
            System.out.println("\n\n" + heap);
        }


        System.out.println("\n\nAfter extracting min " + heap.extractMin() + " from heap 1:");
        System.out.println("\n\n" + heap);

        for (Integer element : list2) {
            System.out.println("\n\nAfter inserting element " + element + " in heap 2:");
            heap2.insert(element);
            System.out.println("\n\n" + heap2);
        }

        System.out.println("\n\nAfter extracting min " + heap2.extractMin() + " from heap 2:");
        System.out.println("\n\n" + heap2);


        System.out.println("\n\nAfter union of heap 1 with heap 2:");
        heap.union(heap2);
        heap2 = null;
        System.out.println("\n\n" + heap);

        System.out.println("\n\nAfter extracting min " + heap.extractMin() + " from heap 1:");
        System.out.println("\n\n" + heap);

        System.out.println("\n\nAfter extracting min " + heap.extractMin() + " from heap 1:");
        System.out.println("\n\n" + heap);

        System.out.println("\n\nAfter extracting min " + heap.extractMin() + " from heap 1:");
        System.out.println("\n\n" + heap);

        System.out.println("\n\nAfter extracting min " + heap.extractMin() + " from heap 1:");
        System.out.println("\n\n" + heap);

        System.out.println("\n\nAfter deleting node 6 from heap 1:");
        Node<Integer> node = heap.getNodeByValue(6, null);
        heap.deleteNode(node);
        System.out.println("\n\n" + heap);

        System.out.println("\n\nAfter deleting node 5 from heap 1:");
        node = heap.getNodeByValue(5, null);
        heap.deleteNode(node);
        System.out.println("\n\n" + heap);

        heap2 = new FibonacciMinHeap<>(Integer.MIN_VALUE);
        for (Integer element : list) {
            System.out.println("\n\nAfter inserting element " + element + " in heap 2:");
            heap2.insert(element);
            System.out.println("\n\n" + heap2);
        }

        System.out.println("\n\nAfter extracting min " + heap2.extractMin() + " from heap 2:");
        System.out.println("\n\n" + heap2);

        System.out.println("\n\nAfter union of heap 1 with heap 2:");
        heap.union(heap2);
        heap2 = null;
        System.out.println("\n\n" + heap);


        System.out.println("\n\nAfter deleting node 11 from heap 1:");
        node = heap.getNodeByValue(11, null);
        heap.deleteNode(node);
        System.out.println("\n\n" + heap);

        System.out.println("\n\nAfter deleting node 3 from heap 1:");
        node = heap.getNodeByValue(3, null);
        heap.deleteNode(node);
        System.out.println("\n\n" + heap);

    }
}
