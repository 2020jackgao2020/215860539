package eecs2011.project;

import java.util.Objects;

public class Node {
    private final int id;
    private int time;

    private int startTime;
    private int endTime;
    private boolean isReady;

    private final Node[] next;
    private int np;

    private final Node[] from;
    private int fp;

    public Node(int id, int time, int size) {
        this.id = id;
        this.time = time;
        this.next = new Node[size];
        this.np = 0;
        this.from = new Node[size];

        this.resetTime();
    }

    public int getId() {
        return id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void addNode(Node n) {
        boolean added = false;
        n.from[n.fp++] = this;
        next[np++] = n;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void resetTime() {
        this.startTime = -1;
        this.endTime = -1;
        this.isReady = false;
    }

    public void updateReady() {
        for (int i = 0; i < fp; i++) {
            if (!from[i].isEnd()) {
                isReady = false;
                return;
            }
        }
        isReady = true;
    }

    public void execute() {
        this.endTime = this.startTime + this.time;
        for (int i = 0; i < np; i++) {
            this.next[i].updateReady();
        }
    }

    public boolean isReady() {
        return isReady;
    }

    public boolean isEnd() {
        return endTime != -1;
    }

    private static void sortNodes(Node[] l, int length) {
        for (int i = 0; i < length; i++) {
            int min = i;
            for (int j = i + 1; j < length; j++) {
                if (l[min].time > l[j].time) {
                    min = j;
                    break;
                }
            }
            Node tmp = l[i];
            l[i] = l[min];
            l[min] = tmp;
        }
    }

    public void sortNode() {
        sortNodes(next, np);
        sortNodes(from, fp);
    }

    public int getNextNodeSize() {
        return np;
    }

    public Node[] getNextNodes() {
        Node[] ret = new Node[np];
        for (int i = 0; i < np; i++) {
            ret[i] = next[i];
        }
        return ret;
    }

    public int getFromNodeSize() {
        return fp;
    }

    public Node[] getFromNodes() {
        Node[] ret = new Node[fp];
        for (int i = 0; i < fp; i++) {
            ret[i] = from[i];
        }
        return ret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return getId() == node.getId() &&
                getTime() == node.getTime();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTime());
    }
}
