public interface AbstractStack<E> extends Iterable<E> {
    void push(E element); // Pushes an element onto the stack
    E pop();              // Pops the top element from the stack
    E peek();             // Peeks at the top element
    int size();           // Gets the size of the stack
    boolean isEmpty();    // Checks if the stack is empty
}