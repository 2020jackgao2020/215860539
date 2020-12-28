package eecs2011.project;

public class Cluster {
    private final int nodes;
    private final Node start;
    private final Node end;
    private Node[] map;

    public Cluster(int nodes) {
        this.nodes = nodes;
        this.map = new Node[nodes];
        this.start = new Node(0, 0, nodes + 1);
        this.start.setStartTime(0);
        this.start.execute();
        this.end = new Node(nodes + 1, 0, nodes + 1);
        for (int i = 0; i < nodes; i++) {
            map[i] = new Node(i + 1, 0, nodes + 1);
        }
    }

    public void setNode(int i, int time) {
        map[i - 1].setTime(time);
    }

    public void execute_one() {
        this.sort();
        this.reset();

        int runtime = 0;
        int ended = 0;
        Node cur = null;

        NodeHeap h = new NodeHeap(nodes + 1);
        start.execute();
        for (Node n : start.getNextNodes()) {
            if (n.isReady()) {
                h.insert(n);
            }
        }

        while (!h.isEmpty() || ended < nodes) {
            // assign
            cur = h.pop();
            cur.setStartTime(runtime);
            cur.execute();
            runtime = cur.getEndTime();
            System.out.println(cur.getId() + "  " + cur.getStartTime());
            ended++;
            for (Node n : cur.getNextNodes()) {
                if (n.isReady() && n.getId() != nodes + 1) {
                    h.insert(n);
                }
            }
        }
        System.out.print(runtime);
    }

    public void execute_app() {
        execute(nodes);
    }

    public void execute(int x) {
        this.sort();
        this.reset();

        int runtime = 0;
        int ended = 0;
        Node[] slot = new Node[x];

//        Node[] e = new Node[nodes];
//        int pos = 0;
        PriorityNodeHeap h = new PriorityNodeHeap(nodes + 1);

        start.setTime(0);
        start.execute();
        for (Node n : start.getNextNodes()) {
            if (n.isReady()) {
                h.insert(n);
            }
        }

        while (!h.isEmpty() || ended < nodes) {
            // assign slot
            for (int i = 0; i < slot.length && !h.isEmpty(); i++) {
                if (slot[i] == null) {
                    Node n = h.pop();
                    n.setStartTime(runtime);
                    slot[i] = n;
                }
            }

            // execute
            int minEnd = Integer.MAX_VALUE;
            for (int i = 0; i < slot.length; i++) {
                if (slot[i] != null) {
                    if (!slot[i].isEnd()) {
                        slot[i].execute();
                        ended++;
                    }
                    if (minEnd > slot[i].getEndTime()) {
                        minEnd = slot[i].getEndTime();
                    }
                }
                runtime = minEnd;
            }

            // finish task
            for (int i = 0; i < slot.length; i++) {
                if (slot[i] != null && slot[i].getEndTime() <= runtime) {
//                    System.out.println(slot[i].getId() + "");
                    for (Node e : slot[i].getNextNodes()) {
                        if (e.isReady() && !e.isEnd() && e.getId() != nodes + 1) {
                            h.insert(e);
                        }
                    }
                    slot[i] = null;
                }
            }
        }

        for (int i = 0; i < map.length; i++) {
            System.out.println(map[i].getId() + "  " + map[i].getStartTime());
        }
        System.out.print(runtime);
    }

    public void sort() {
        this.start.sortNode();
        for (Node n : this.map) {
            n.sortNode();
        }
    }

    public void connect(int i, int j) throws NodeConnectException {
        if (i == this.end.getId()) throw new NodeConnectException("Cannot connect end to other node");
        if (i > nodes || i < 0 || j > nodes + 1 || j < 0) throw new IllegalArgumentException("wrong node id");
        if (i == 0 && j == nodes + 1) return;
//        if (i == 0 && j == nodes + 1) throw new NodeConnectException("Cannot connect start to end node");
        if (i == j) throw new NodeConnectException("Connect to self");
        if (i == 0) {
            this.start.addNode(map[j - 1]);
        } else if (j == nodes + 1) {
            this.map[i - 1].addNode(this.end);
        } else {
            this.map[i - 1].addNode(this.map[j - 1]);
        }
    }

    public boolean check() {
        boolean[] accessed = new boolean[nodes + 1];
        if (!checkCycle(start, new Stack<>(), accessed)) return false; //&& checkAccessed();
        for (int i = 0; i < accessed.length; i++) {
            if (!accessed[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean checkCycle(Node n, Stack<Integer> list, boolean[] accessed) {
        if (n != null) {
            if (list.find(n.getId())) {
                return false;
            }
            if (n != start)
                accessed[n.getId() - 1] = true;
            if (n == end) return true;
            list.push(n.getId());
            for (Node next : n.getNextNodes()) {
                if (!checkCycle(next, list, accessed)) {
                    return false;
                }
            }
            list.pop();
        } else {
            return list.peek() == end.getId();
        }
        return true;
    }

//    public boolean checkAccessed() {
//        boolean[] accessed = new boolean[nodes + 1];
//        for (int i = 0; i < accessed.length; i++) {
//            accessed[i] = false;
//        }
//
//        Queue<Node> queue = new Queue<>();
//        queue.push(start);
//        while (!queue.isEmpty()) {
//            Node n = queue.pop();
//            if (n != start)
//                accessed[n.getId() - 1] = true;
//            for (Node c : n.getNextNodes()) {
//                if (!accessed[c.getId() - 1])
//                    queue.push(c);
//            }
//        }
//
//        for (int i = 0; i < accessed.length; i++) {
//            if (!accessed[i]) {
//                return false;
//            }
//        }
//        return true;
//    }

    public void reset() {
        for (Node node : map) {
            node.resetTime();
        }
    }
}
