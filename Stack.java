/**
 * Generic Stack implementation using linked list.
 * @param <E> type of data
 */
public class Stack<E> {
    private Node<E> top;
    private int size;

    /**
     * Default Constructor used to initialize the attributes of the Stack
     */
    public Stack() {
        this.top = null;
        this.size = 0;
    }

    /**
     * Returns the current size of the stack
     * @return {@code int} - current size of stack
     */
    public int getSize() { return size; }

    /**
     * Tests whether the stack is empty.
     * @return {@code boolean} - {@code true} if the stack is empty, {@code false} otherwise
     */
    public boolean isEmpty() { return getSize() == 0; }

    /**
     * Inserts an element at the top of the stack.
     * @param data the element to be inserted
     */
    public void push(E data) {
        // YOUR CODE HERE

        // If stack is empty, create new node, set new node as top. New node points to null
        if (isEmpty())
            top = new Node<>(data, null);
        // If stack is not empty, create new node, set next to current top. Assign new node to top.
        else
            top = new Node<>(data, top);
        size++;
    }

    /**
     * Removes and returns the top element from the stack.
     * @return element removed from top the stack (or {@code null} if empty)
     */
    public E pop() {
        // YOUR CODE HERE

        // Check if stack is empty. If yes, return null
        if (isEmpty())
            return null;

        // Retrieve data of top element
        E poppedData = top.getData();
        // Point top to next element
        top = top.getNext();
        size--;
        return poppedData;
    }

    /**
     * Returns, but does not remove, the element at the top of the stack.
     * @return top element in the stack (or {@code null} if empty)
     */
    public E peekTop() {
        // YOUR CODE HERE

        // Check if stack is empty. If yes, return null
        if (isEmpty())
            return null;

        // Retrieve data of top element
        return top.getData();
    }

    /**
     * Method to print the stack class in desired format.
     * @return output string in desired format.
     */
    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        if (isEmpty()) return "Stack is empty";

        Node<E> curr = this.top;
        while (curr != null) {
            temp.insert(0, String.format("%s ", curr.getData().toString()));
            curr = curr.getNext();
        }

        return temp.toString();
    }
}
