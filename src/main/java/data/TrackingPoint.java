package data;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "trkpt")
public class TrackingPoint {
    @XmlAttribute
    private double lat;
    @XmlAttribute
    private double lon;
    private double ele;

    @XmlJavaTypeAdapter(XmlDateAdapter.class)
    private LocalDateTime time;
}
