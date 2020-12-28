package eecs2011.project;

public class PriorityNodeHeap extends NodeHeap {

    public PriorityNodeHeap(int maxSize) {
        super(maxSize);
    }

    @Override
    protected boolean compare(Node a, Node b) {
        if (a.getFromNodes().length == b.getFromNodes().length) {
            return a.getTime() < b.getTime();
        }
        return a.getNextNodeSize() > b.getNextNodeSize();
    }
}
