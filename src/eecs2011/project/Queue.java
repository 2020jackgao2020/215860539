package eecs2011.project;

public class Queue<T> {
    private ListNode<T> top;
    private ListNode<T> last;
    private int length = 0;

    public Queue() {
        this.top = new ListNode<>(null);
        this.last = top;
    }

    public int length() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public void push(T d) {
        ListNode<T> n = new ListNode<>(d);
        last.setNext(n);
        last = n;
        length++;
    }

    public T pop() {
        ListNode<T> n = top.getNext();
        if (n == null) {
            return null;
        }
        top.setNext(n.getNext());
        if (n.getNext() == null)
            last = top;
        length--;
        return n.getData();
    }

}
