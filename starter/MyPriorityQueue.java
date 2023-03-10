import java.util.Collection;

public class MyPriorityQueue<E extends Comparable<E>>{
    protected MyMinHeap<E> heap;

    public MyPriorityQueue(){
        heap = new MyMinHeap<>();
    }

    public MyPriorityQueue(Collection<? extends E> collection){
        heap = new MyMinHeap<>(collection);
    }

    public void push(E element){
        if(element == null)
            throw new NullPointerException();
        
        heap.insert(element);
    }

    public E peek(){
        return heap.getMin();
    }

    public E pop(){
        return heap.remove();
    }

    public int getLength(){
        return heap.size();
    }

    public void clear(){
        heap.clear();
    }
}