package data;

import jakarta.xml.bind.JAXB;

import java.io.File;
import java.util.List;
import java.util.Random;

public class TrkProducer implements Runnable {
    private final Queue queue;
    private final List<File> fileList;

    public TrkProducer(Queue queue, List<File> fileList) {
        this.queue = queue;
        this.fileList = fileList;
        System.out.println(fileList);
    }


    @Override
    public void run() {
        Random random = new Random();
        /*while (true) {
            try {
                Trk trk = JAXB.unmarshal(fileList.get(random.nextInt(0, fileList.size())), Trk.class);
                queue.put(trk);
                Thread.sleep(random.nextInt(900, 1100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

        fileList.forEach(file -> {
            try {
                Trk trk = JAXB.unmarshal(file, Trk.class);
                queue.push(trk);
                Thread.sleep(random.nextInt(900, 1100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
