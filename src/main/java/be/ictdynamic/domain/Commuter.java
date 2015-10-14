package be.ictdynamic.domain;

import java.io.Serializable;

/**
 * @author $Author: $
 * @version $Revision: $
 * @date $Date: $
 */
public class Commuter implements Serializable {
    private String officeStreet;
    private String officeCommune;
    private String officeCountry;

    private String homeStreet;
    private String homeCommune;
    private String homeCountry;

    public Commuter() {
        officeStreet = "Tweebunder 4";
    }

    public Commuter(String officeStreet, String officeCommune, String officeCountry) {
        this.officeStreet = officeStreet;
        this.officeCommune = officeCommune;
        this.officeCountry = officeCountry;
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
                "officeStreet='" + officeStreet + '\'' +
                ", officeCommune='" + officeCommune + '\'' +
                ", officeCountry='" + officeCountry + '\'' +
                '}';
    }

}
