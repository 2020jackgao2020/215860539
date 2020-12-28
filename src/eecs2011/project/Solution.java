package eecs2011.project;

import java.util.Scanner;

/**
 * The Solution class is where you write your solutions.
 * Please do NOT modify the method declarations and the package name.
 * You can add other classes to the package if needed.
 * Read data from standard input and write your answer to standard output.
 * We use pipelines to send input to your program and send your output to our judge system.
 * Please do NOT read and write data from and to the disk.
 * You should strictly follow the sample output format.
 * Please do not use any built-in/third-party data structures or algorithms.
 * Memory Limit: 256MB  Time Limit: 10s
 */
public class Solution {

    private Cluster read() throws NodeConnectException {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(); // number of functions
        Cluster ret = new Cluster(n);
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                int c = input.nextInt();
                if (c != 0 && c != 1) throw new IllegalArgumentException("invalid file");
                if (c != 0) {
                    ret.connect(i, j);
                }
            }
        }
        for (int i = 1; i < n + 1; i++) {
            ret.setNode(i, input.nextInt()); // read response time from standard input
        }
//        if (input.hasNext()) {
//            throw new IllegalArgumentException("invalid file");
//        }
//        System.out.println("end");
        return ret;
    }

    /**
     * Solution to Part 1
     */
    public void check_validity() {
        ; // read from standard input
        ; // do something
        Cluster c = null;
        try {
            c = read();
            if (c.check()) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        } catch (NodeConnectException e) {
            System.out.println("False");
        }
    }

    /**
     * Solution Part 2
     */
    public void schedule_1() {
        ; // read from standard input
        ; // do something
        ; // write your answer to standard output
        Cluster c = null;
        try {
            c = read();
            c.execute_one();
        } catch (NodeConnectException e) {
            System.out.println("False");
        }
    }

    /**
     * Solution to Part 3
     */
    public void schedule_x() {
        ; // read from standard input
        ; // do something
        ; // write your answer to standard output
        Cluster c = null;
        try {
            c = read();
            c.execute_app();
        } catch (NodeConnectException e) {
            System.out.println("False");
        }
    }

    /**
     * Solution to Part 4 (optional)
     */
    public void schedule_2() {
        ; // read from standard input
        ; // do something
        ; // write your answer to standard output
        Cluster c = null;
        try {
            c = read();
            c.execute(2);
        } catch (NodeConnectException e) {
            System.out.println("False");
        }
    }
}
