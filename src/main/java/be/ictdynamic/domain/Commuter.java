package be.ictdynamic.domain;

import java.io.Serializable;

/**
 * Class Commuter.
 *
 * @author Wim Van den Brande
 * @since 03/10/2015 - 20:35
 */
public class Commuter implements Serializable {

    private static final long serialVersionUID = -5073135041934007777L;

    private String dummy;
    private String dummy2;

    private String homeStreet;
    private String homeCommune;
    private String homeCountry;

    private String distance;
    private String duration;

    public Commuter() {
        homeStreet = "Tweebunder 4";
        homeCommune = "Edegem";
        homeCountry = "Belgium";
    }

    public Commuter(String homeStreet, String homeCommune, String homeCountry) {
        this.homeStreet = homeStreet;
        this.homeCommune = homeCommune;
        this.homeCountry = homeCountry;
    }

    public String getHomeStreet() {
        return homeStreet;
    }

    public void setHomeStreet(String homeStreet) {
        this.homeStreet = homeStreet;
    }

    public String getHomeCommune() {
        return homeCommune;
    }

    public void setHomeCommune(String homeCommune) {
        this.homeCommune = homeCommune;
    }

    public String getHomeCountry() {
        return homeCountry;
    }

    public void setHomeCountry(String homeCountry) {
        this.homeCountry = homeCountry;
    }

    public String getDummy() {
        return dummy;
    }

    public void setDummy(String dummy) {
        this.dummy = dummy;
    }

    public String getDummy2() {
        return dummy2;
    }

    public void setDummy2(String dummy2) {
        this.dummy2 = dummy2;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Commuter{" +
                "homeStreet='" + homeStreet + '\'' +
                ", homeCommune='" + homeCommune + '\'' +
                ", homeCountry='" + homeCountry + '\'' +
                '}';
    }

}
