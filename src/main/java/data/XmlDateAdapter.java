package data;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class XmlDateAdapter extends XmlAdapter<String, LocalDateTime> {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ISO_INSTANT;

    @Override
    public LocalDateTime unmarshal(String s) throws Exception {
        return LocalDateTime.parse(s, DTF);
    }

    @Override
    public String marshal(LocalDateTime localDateTime) throws Exception {
        return DTF.format(localDateTime);
    }
}
