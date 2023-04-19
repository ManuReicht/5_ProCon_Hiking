package observer;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.Result;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogObserver implements Observer {

    private final File file;
    private final ObjectMapper om;
    private final FileWriter fw;


    public LogObserver(File file) throws IOException {
        this.file = file;
        om = new ObjectMapper();
        file.delete();
        fw = new FileWriter(file, true);
    }

    @Override
    public void update(Result result) {
        synchronized (file) {
            try {
                String json = om.writeValueAsString(result);
                fw.write(json);
                fw.write(System.lineSeparator());
                fw.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
