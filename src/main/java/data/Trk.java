package data;


import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "trk")
public class Trk {
    private String name;
    @XmlElementWrapper(name = "trkseg")
    @XmlElement(name = "trkpt")
    private List<TrackingPoint> trackingPoints;
}
