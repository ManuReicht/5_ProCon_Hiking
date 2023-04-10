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
                queue.wait();
            }
            queue.add(trk);
            printQueue();
            queue.notifyAll();
        }
    }

    public Trk get() throws InterruptedException {
        synchronized (queue) {
            while (isEmpty()) {
                queue.wait();
            }
            Trk trk = queue.remove(0);
            printQueue();
            queue.notifyAll();
            return trk;
        }
    }

    public void printQueue() {
        String status = "";
        for (int i = 0; i < queue.size(); i++) {
            status += "*";
        }
        for (int i = 0; i < MAX_SIZE - queue.size(); i++) {
            status += "-";
        }
        System.out.println("-----------------------");
        System.out.println("| Queue status: " + status + " |");
        System.out.println("-----------------------");
    }
}
