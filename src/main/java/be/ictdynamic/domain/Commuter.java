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

    private String homeStreet;
    private String homeCommune;
    private String homeCountry;

    public Commuter() {
        homeStreet = "Tweebunder";
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

    @Override
    public String toString() {
        return "Commuter{" +
                "homeStreet='" + homeStreet + '\'' +
                ", homeCommune='" + homeCommune + '\'' +
                ", homeCountry='" + homeCountry + '\'' +
                '}';
    }

}
