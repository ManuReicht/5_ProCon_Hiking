package data;

import java.util.ArrayList;
import java.util.List;

public class Queue {
    private final List<Trk> trks;
    private final int MAX_SIZE;

    public Queue(int MAX_SIZE) {
        this.MAX_SIZE = MAX_SIZE;
        trks = new ArrayList<>();
    }


    public boolean isEmpty() {
        return trks.isEmpty();
    }

    public boolean isFull() {
        return trks.size() >= MAX_SIZE;
    }

    public void push(Trk trk) throws InterruptedException {
        synchronized (trks) {
            while (isFull()) {
                trks.wait();
            }
            trks.add(trk);
            printQueue();
            trks.notifyAll();
        }
    }

    public Trk pop() throws InterruptedException {
        synchronized (trks) {
            while (isEmpty()) {
                trks.wait();
            }
            Trk trk = trks.remove(0);
            printQueue();
            trks.notifyAll();
            return trk;
        }
    }

    public void printQueue() {
        String status = "";
        for (int i = 0; i < trks.size(); i++) {
            status += "*";
        }
        for (int i = 0; i < MAX_SIZE - trks.size(); i++) {
            status += "-";
        }
        System.out.println("-----------------------");
        System.out.println("| Queue status: " + status + " |");
        System.out.println("-----------------------");
    }
}
