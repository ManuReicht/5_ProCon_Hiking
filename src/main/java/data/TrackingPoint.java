package data;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "trkpt")
public class TrackingPoint {
    @XmlAttribute(name = "lat")
    private double lat;
    @XmlAttribute(name = "lon")
    private double lon;
    @XmlAttribute(name = "ele")
    private double ele;

    @XmlJavaTypeAdapter(XmlDateAdapter.class)
    @XmlAttribute(name = "time")
    private LocalDateTime time;
}
