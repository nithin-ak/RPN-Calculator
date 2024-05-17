/**
 * Node class to store a single data item.
 * @param <E> type of data
 * @author Angeline Tan
 * @since 15 Aug 2022
 */

public class Node <E>{
    private E data;
    private Node<E> next;

    /**
     * Constructor to initialize node
     * @param e data for the node
     * @param n address to next node
     */
    public Node(E e, Node<E> n) {
        this.data = e;
        this.next = n;
    }

    /**
     * Accessor method
     * @return value of data
     */
    public E getData() { return data; }

    /**
     * Mutator method
     * @param d sets value of data
     */
    public void setData(E d) { this.data = d; }

    /**
     * Accessor Method
     * @return address of next node
     */
    public Node<E> getNext() { return next; }

    /**
     * Mutator method
     * @param n sets value to address of next node
     */
    public void setNext(Node<E> n) { next = n; }

    /**
     * Method to print node in desired format
     * @return string output in desired format
     */
    @Override
    public String toString() {
        return String.format("Data: %s\n", this.data);
    }
}
