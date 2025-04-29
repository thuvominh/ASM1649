import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements AbstractLinkedList<E>{
    private Node<E> head;
    private Node<E> tail;
    private int size;
    private static class Node<E>{
        private E element;
        private Node<E> next;
        private Node(E value){
            this.element = value;
        }
    }
    public SinglyLinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    @Override
    public void addFirst(E element){
        Node<E> newNode = new Node<>(element);
        if (head == null){
            tail = newNode;
        }else{
            newNode.next = head;
        }
        head = newNode;
        size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> newNode = new Node<>(element);
        if (tail == null){
            head = newNode;
        }else{
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        Node<E> node = head;
        head = head.next;
        node.next = null;
        if (isEmpty()){
            head = tail = null;
        }
        size--;
        return node.element;
    }

    @Override
    public E removeLast() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        Node<E> current = head;
        E lastElement = tail.element;
        if (size == 1){
            tail = head = null;
            return lastElement;
        }
        while (current.next != tail){
            current = current.next;
        }
        current.next = null;
        tail= current;
        size--;
        return lastElement;
    }

    @Override
    public E getFirst() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return head.element;
    }

    @Override
    public E getLast() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return tail.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
    public boolean contains(E element){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        Node<E> current = head;
        while (current.element!=null){
            if (current.element == element){
                return true;
            }else current = current.next;
        }
        return false;
    }
    public E remove (E element){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        if (element == head.element){
            return removeFirst();
        }
        Node<E> current= head;
        while (current.next!= null){
            if (current.next.element == element){
                if (current.next == tail){
                    E lastElement = tail.element;
                    current.next = null;
                    tail= current;
                    size--;
                    return lastElement;
                }
                Node<E> tmp = current.next;
                current.next = current.next.next;
                tmp.next = null;
                size--;
                return tmp.element;
            }else{
                current = current.next;}
        }
        if (size == 0){
            head = tail = null;
        }
        return null;
    }
    @Override
    public String toString(){
        Node<E> node = head;
        StringBuilder result = new StringBuilder();
        result.append("[");
        while (node != null){
            result.append(node.element);
            if (node.next != null){
                result.append(", ");
            }
            node = node.next;
        }
        result.append("]");
        return result.toString();
    }
}