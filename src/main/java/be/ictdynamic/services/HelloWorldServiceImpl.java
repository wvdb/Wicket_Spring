package be.ictdynamic.services;

import org.springframework.stereotype.Service;

/**
 * Class HelloWorldService.
 *
 * @author Wim Van den Brande
 * @since 03/10/2015 - 20:35
 */
@Service
public class HelloWorldServiceImpl implements HelloWorldService {
    String country;

    public HelloWorldServiceImpl() {
        this.country = "Belgium";
    }

    @Override
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
