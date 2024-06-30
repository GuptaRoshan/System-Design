package LD;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {

    private final PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
    private final PriorityQueue<Integer> large = new PriorityQueue<>();
    private boolean even = true;

    public double findMedian() {
        assert small.peek() != null;
        assert large.peek() != null;

        if (even) {
            return (small.peek() + large.peek()) / 2.0;
        } else {
            return small.peek();
        }
    }

    public void addNum(int num) {
        if (even) {
            large.offer(num);
            small.offer(large.poll());
        } else {
            small.offer(num);
            large.offer(small.poll());
        }
        even = !even;
    }


    public static void main(String[] args) throws InterruptedException {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(3);
        medianFinder.addNum(4);
        medianFinder.addNum(67);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
    }

}
