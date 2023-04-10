package data;

import java.util.Random;

public class TrkConsumer implements Runnable {
    private final Queue queue;

    public TrkConsumer(Queue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            try {
                Trk trk = queue.get();
                Thread.sleep(random.nextInt(900, 1100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
