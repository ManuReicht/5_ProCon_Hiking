package xmladapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.Instant;

public class InstantAdapter extends XmlAdapter<String, Instant> {

    @Override
    public Instant unmarshal(String s) throws Exception {
        return Instant.parse(s);
    }

    @Override
    public String marshal(Instant instant) throws Exception {
        return instant.toString();
    }
}
