package data;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "trk")
public class Trk {
    @XmlAttribute(name = "name")
    private String name;
    @XmlElementWrapper(name = "trkseg")
    @XmlElement(name = "trkpt")
    private List<TrackingPoint> trackingPoints;
    
}
