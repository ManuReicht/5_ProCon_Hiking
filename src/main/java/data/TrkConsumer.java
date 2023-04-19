package data;

import observer.Subject;
import strategy.Strategy;

import java.util.List;
import java.util.Random;

public class TrkConsumer extends Subject implements Runnable {
    private final Queue queue;
    private final List<Strategy> strategies;

    public TrkConsumer(Queue queue, List<Strategy> strategies) {
        this.queue = queue;
        this.strategies = strategies;
    }

    @Override
    public void run() {
        Random random = new Random();
        //for(;;) {
        while (true) {
            try {
                Trk trk = queue.pop();
                Result result = new Result();
                result.setName(trk.getName());
                Long startTime = System.nanoTime();
                strategies.forEach(strategy -> strategy.calculate(trk, result));
                Long endTime = System.nanoTime();
                result.setAnalyzeTime(endTime - startTime);

                notifyObservers(result);

                //Thread.sleep(random.nextInt(900, 1100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
