import data.Queue;
import data.TrkConsumer;
import data.TrkProducer;
import observer.LogObserver;
import observer.PrintObserver;
import strategy.CalculateDistance;
import strategy.CalculateElevation;
import strategy.Strategy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try {
            Main main = new Main();
            List<List<File>> partitionedList = main.getFiles();

            Queue queue = new Queue(5);

            File logFile = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "out.log").toFile();
            PrintObserver printObserver = new PrintObserver();
            LogObserver logObserver = new LogObserver(logFile);


            TrkProducer pro1 = new TrkProducer(queue, partitionedList.get(0));
            TrkProducer pro2 = new TrkProducer(queue, partitionedList.get(1));
            TrkProducer pro3 = new TrkProducer(queue, partitionedList.get(2));
            TrkProducer pro4 = new TrkProducer(queue, partitionedList.get(3));

            Thread tPro1 = new Thread(pro1);
            Thread tPro2 = new Thread(pro2);
            Thread tPro3 = new Thread(pro3);
            Thread tPro4 = new Thread(pro4);

            List<Strategy> strategies = Arrays.asList(new CalculateDistance(), new CalculateElevation());

            TrkConsumer con1 = new TrkConsumer(queue, strategies);
            TrkConsumer con2 = new TrkConsumer(queue, strategies);
            TrkConsumer con3 = new TrkConsumer(queue, strategies);

            con1.attach(printObserver);
            con1.attach(logObserver);
            con2.attach(printObserver);
            con2.attach(logObserver);
            con3.attach(printObserver);
            con3.attach(logObserver);

            Thread tCon1 = new Thread(con1);
            Thread tCon2 = new Thread(con2);
            Thread tCon3 = new Thread(con3);


            tPro1.start();
            tPro2.start();
            tPro3.start();
            tPro4.start();

            tCon1.start();
            tCon2.start();
            tCon3.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<List<File>> getFiles() {
        File dir = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "hiking").toFile();
        List<File> fileList = Arrays.stream(dir.listFiles()).collect(Collectors.toList());
        int chunkSize = fileList.size() / 4;
        AtomicInteger counter = new AtomicInteger();
        final List<List<File>> partitionedList =
                fileList.stream().collect(Collectors.groupingBy(i -> counter.getAndIncrement() / chunkSize))
                        .values()
                        .stream().collect(Collectors.toList());

        return partitionedList;
    }

}
