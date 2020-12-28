package eecs2011.project;

public class NodeHeap {
    private Node[] heap;
    private int heapSize;

    public NodeHeap( int maxSize ) {
        heap = new Node[ maxSize ];
        heapSize = 0;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public boolean isFull() {
        return heapSize >= heap.length;
    }

    public static int parent( int i ) {
        return (i-1)/2;
    }
    public static int rightChild( int i ) {
        return 2 * i + 2;
    }
    public static int leftChild( int i ) {
        return 2 * i + 1;
    }

    public void insert( Node n ) {

        if ( ! isFull() ) {
            heap[ heapSize ] = n;
            heapSize++;
            siftUp( heapSize-1 );
        } // end if

    }

    public boolean find(Node node) {
        for (Node n : heap) {
            if (n.equals(node)) {
                return true;
            }
        }
        return false;
    }

    protected void siftUp( int index ) {
        Node toSift = heap[index];
        int i = index;
        int parent = parent( index );

        while ( i > 0 && !compare(heap[parent], toSift)) {
            heap[i] = heap[parent];
            i = parent;
            parent = parent( i );
        } // end while
        heap[i] = toSift;
    }

    public Node pop() {
        if (heapSize == 0) return null;
        Node ret = heap[0];
        heap[0] = heap[--heapSize];
        shiftDown(0);
        return ret;
    }

    protected void shiftDown(int index) {
        int i = index;
        Node max;
        int toShift = 0;

        while (i < heapSize && toShift != -1) {
            max = heap[i];
            toShift = -1;
            if (leftChild(i) < heapSize && compare(heap[leftChild(i)], max)) {
                toShift = leftChild(i);
                max = heap[toShift];
            }
            if (rightChild(i) < heapSize && compare(heap[rightChild(i)], max)) {
                toShift = rightChild(i);
                max = heap[toShift];
            }
            if (toShift != -1) {
                heap[toShift] = heap[i];
                heap[i] = max;
                i = toShift;
            }
        }
    }

    protected boolean compare(Node a, Node b) {
        return a.getTime() < b.getTime();
    }
}
