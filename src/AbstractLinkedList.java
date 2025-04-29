import java.util.Iterator;

public interface AbstractLinkedList<E> extends Iterable<E> {
    public void addFirst(E element);
    public void addLast(E element);
    public E removeFirst();
    public E removeLast();
    public E getFirst();
    public E getLast();
    public int size();
    public boolean isEmpty();
}