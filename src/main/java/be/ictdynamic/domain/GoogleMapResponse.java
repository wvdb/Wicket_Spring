package be.ictdynamic.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

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
    private Collection<Voyage> voyages;

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

    public Collection<Voyage> getVoyages() {
        if (voyages == null) {
            voyages = new ArrayList<>();
        }
        return voyages;
    }

    public void setVoyages(Collection<Voyage> voyages) {
        this.voyages = voyages;
    }

    @Override
    public String toString() {
        return "GoogleMapResponse{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }

    public static class Voyage {
       private Integer voyageDuration;
       private Integer voyageDistance;
       private Calendar voyageStartTime;

        public Integer getVoyageDuration() {
            return voyageDuration;
        }

        public void setVoyageDuration(Integer voyageDuration) {
            this.voyageDuration = voyageDuration;
        }

        public Integer getVoyageDistance() {
            return voyageDistance;
        }

        public void setVoyageDistance(Integer voyageDistance) {
            this.voyageDistance = voyageDistance;
        }

        public Calendar getVoyageStartTime() {
            return voyageStartTime;
        }

        public void setVoyageStartTime(Calendar voyageStartTime) {
            this.voyageStartTime = voyageStartTime;
        }
    }
}
