import java.lang.reflect.Array;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;

public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface<E>{
    protected ArrayList<E> data;
    private static final int DOUBLE = 2;


    public MyMinHeap(){
        data = new ArrayList<>();
    }

    /**
     * Ask if the collection shit is right
     * Ask if the unchecked is ok
     * @param collection
     */
    public MyMinHeap(Collection<? extends E> collection){
        if(collection == null)
            throw new NullPointerException();

        for (E e : collection) {
            if(e == null)
                throw new NullPointerException();
        }

        data = new ArrayList<>(collection);

        for(int i = data.size() - 1; i >= 0; i--){
           percolateDown(i);
        }
    }

    //ask if the execptions are ok
    protected void swap(int from, int to){
        if(from < 0 || from >= data.size() || to < 0 || to >= data.size())
            throw new IndexOutOfBoundsException();
        
        E temp = data.get(to);
        data.set(to, data.get(from));

        data.set(from, temp);

    }

    protected static int getParentIdx(int index){
        if(index <= 0)
            throw new InvalidParameterException();
        
        return index/DOUBLE;
    }

    //does it matter if index is outofbounds?
    protected static int getLeftChildIdx(int index){
        if(index < 0)
            throw new InvalidParameterException();

        return index*DOUBLE + 1;
    }

    protected static int getRightChildIdx(int index){
        if(index < 0)
            throw new InvalidParameterException();

        return index*DOUBLE + DOUBLE;
    }

    protected int getMinChildIdx(int index){
        if(index < 0 || index >= data.size())
            throw new IndexOutOfBoundsException();

        if(getLeftChildIdx(index) >= data.size())
            return -1;
        
        if(getRightChildIdx(index) >= data.size())
            return getLeftChildIdx(index);

        return data.get(getLeftChildIdx(index)).compareTo(
                data.get(getRightChildIdx(index))) <= 0 
                ? getLeftChildIdx(index) : getRightChildIdx(index);
    }

    protected void percolateUp(int index){
        if(index < 0 || index >= data.size())
            throw new IndexOutOfBoundsException();
        
        int curr = index;
        int parent;
        while(true){
            parent = getParentIdx(curr);
            if(data.get(curr).compareTo(data.get(parent)) < 0){
                swap(curr, parent);
                curr = parent;
            }
            else return;
        }
    }

    protected void percolateDown(int index){
        if(index < 0 || index >= data.size())
            throw new IndexOutOfBoundsException();
        
        int curr = index;
        int minChild;

        while(true){
            minChild = getMinChildIdx(curr);
            if(minChild == -1)
                return;
            
            if(data.get(curr).compareTo(data.get(minChild)) > 0){
                swap(curr, minChild);
                curr = minChild;
            }
            else return;
            
        }
    }

    protected E deleteIndex(int index){
        if(index < 0 || index >= data.size())
            throw new IndexOutOfBoundsException();

        swap(index, data.size() - 1);

        if(data.get(index).compareTo(data.get(getParentIdx(index))) < 0)
            percolateUp(index);
        else
            percolateDown(index);

        E removed = data.remove(data.size() - 1);

        return removed;

    }

    @Override
    public void insert(E element) {
        if(element == null)
            throw new NullPointerException();

        data.add(element);

        percolateUp(data.size()-1);
    }

    @Override
    public E getMin() {
        if(data == null)
            return null;
        
        return data.get(0);
    }

    //can it be empty and not null?
    @Override
    public E remove() {
        if(data == null || data.size() == 0)
            return null;
        
        return deleteIndex(0);
    }

    @Override
    public int size() {
        return data.size();
    }

    //does this change size? can I set to null?
    @Override
    public void clear() {
        data.clear();
    }   
}