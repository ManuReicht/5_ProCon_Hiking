package data;

import java.util.ArrayList;
import java.util.List;

public class Queue {
    private final List<Trk> queue;
    private final int MAX_SIZE;

    public Queue(int MAX_SIZE) {
        this.MAX_SIZE = MAX_SIZE;
        queue = new ArrayList<>();
    }


    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public boolean isFull() {
        return queue.size() >= MAX_SIZE;
    }

    public void put(Trk trk) throws InterruptedException {
        synchronized (queue) {
            while (isFull()) {
                wait();
            }
            queue.add(trk);
            notifyAll();
        }
    }

    public Trk get() throws InterruptedException {
        synchronized (queue) {
            while (isEmpty()) {
                wait();
            }
            Trk trk = queue.remove(0);
            notifyAll();
            return trk;
        }
    }
}
