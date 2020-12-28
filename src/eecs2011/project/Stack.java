package eecs2011.project;

public class Stack<T> {
    private ListNode<T> top;
    private ListNode<T> last;
    private int length = 0;

    public Stack() {
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
        top = new ListNode<>(d, top);
        length++;
    }

    public T peek() {
        return top.getData();
    }

    public T pop() {
        ListNode<T> n = top;
        if (top.getNext() != null) {
            top = top.getNext();
            length--;
        }

        return n.getData();
    }

    public boolean find(T t) {
        ListNode<T> cur = new ListNode<>(null, top);
        while (cur != null) {
            cur = cur.getNext();
            if (cur.getData() == null)
                return false;
            if (cur.getData().equals(t))
                return true;
        }
        return false;
    }

}
