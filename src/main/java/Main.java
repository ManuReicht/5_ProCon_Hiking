import data.Queue;
import data.TrkConsumer;
import data.TrkProducer;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Main main = new Main();
        List<List<File>> partitionedList = main.getFiles();

        Queue queue = new Queue(5);

        TrkProducer pro1 = new TrkProducer(queue, partitionedList.get(0));
        TrkProducer pro2 = new TrkProducer(queue, partitionedList.get(1));
        TrkProducer pro3 = new TrkProducer(queue, partitionedList.get(2));
        TrkProducer pro4 = new TrkProducer(queue, partitionedList.get(3));

        Thread tPro1 = new Thread(pro1);
        Thread tPro2 = new Thread(pro2);
        Thread tPro3 = new Thread(pro3);
        Thread tPro4 = new Thread(pro4);

        TrkConsumer con1 = new TrkConsumer(queue);
        TrkConsumer con2 = new TrkConsumer(queue);
        TrkConsumer con3 = new TrkConsumer(queue);

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
