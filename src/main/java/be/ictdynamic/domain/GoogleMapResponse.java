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

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "GoogleMapResponse{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
