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

    private String officeStreet;
    private String officeCommune;
    private String officeCountry;

    private String homeStreet;
    private String homeCommune;
    private String homeCountry;

    private String distance;
    private String duration;

    public Commuter() {
        officeStreet = "Interleuvenlaan 17A";
        officeCommune = "Heverlee";
        officeCountry = "Belgium";

        homeStreet = "Tweebunder 4";
        homeCommune = "Edegem";
        homeCountry = "Belgium";
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

    public String getOfficeStreet() {
        return officeStreet;
    }

    public void setOfficeStreet(String officeStreet) {
        this.officeStreet = officeStreet;
    }

    public String getOfficeCommune() {
        return officeCommune;
    }

    public void setOfficeCommune(String officeCommune) {
        this.officeCommune = officeCommune;
    }

    public String getOfficeCountry() {
        return officeCountry;
    }

    public void setOfficeCountry(String officeCountry) {
        this.officeCountry = officeCountry;
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
