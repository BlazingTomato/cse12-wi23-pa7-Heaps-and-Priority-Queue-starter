/**
 * This file contains part of the public tests (visible on Gradescope).
 * Use this as a guide to write tests to verify your MyMinHeap and
 * MyPriorityQueue implementation.
 */

 import org.junit.Test;

 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.List;
 
 import static org.junit.Assert.assertEquals;
 import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
 

 public class HiddenTester {

     static <E extends Comparable<E>> MyMinHeap<E> initMinHeap(List<E> data) {
         MyMinHeap<E> heap = new MyMinHeap<>();
         heap.data = new ArrayList<>(data);
         return heap;
     }
 
    
     static <E extends Comparable<E>> MyPriorityQueue<E> initPriorityQueue(
             List<E> data) {
         MyMinHeap<E> heap = new MyMinHeap<>();
         heap.data = new ArrayList<>(data);
         MyPriorityQueue<E> pq = new MyPriorityQueue<>();
         pq.heap = heap;
         return pq;
     }

    @Test
    public void testSwap() {
        MyMinHeap<Integer> heap = initMinHeap(Arrays.asList(3, 1, 2));
        heap.swap(1, 0);
        assertEquals(Arrays.asList(1, 3, 2), heap.data);
    }
    
    @Test
    public void testMinChildForSame() {
        MyMinHeap<Integer> heap = initMinHeap(Arrays.asList(1,2,3,4,4,5,4));
        int idx = heap.getMinChildIdx(1);
        assertSame(3, idx);

        idx = heap.getMinChildIdx(2);

        assertSame(6,idx);

        heap = initMinHeap(Arrays.asList(1,2,3,4,4,5));
        idx = heap.getMinChildIdx(2);
        assertEquals(5, idx);
    }


    
}