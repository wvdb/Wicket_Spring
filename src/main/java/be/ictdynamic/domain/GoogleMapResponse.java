package be.ictdynamic.domain;

import java.io.Serializable;

/**
 * Class Commuter.
 *
 * @author Wim Van den Brande
 * @since 03/10/2015 - 20:35
 */
public class GoogleMapResponse implements Serializable {

    private static final long serialVersionUID = -5073135041934007777L;

    private Double lat;
    private Double lng;
    private Integer duration;
    private Integer distance;

    public Double getLat() {
        if (lat == null) {
            lat = 0.0;
        }
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        if (lng == null) {
            lng = 0.0;
        }
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Integer getDuration() {
        if (duration == null) {
            duration = 0;
        }

        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getDistance() {
        if (distance == null) {
            distance = 0;
        }
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GoogleMapResponse{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
