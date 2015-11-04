package be.ictdynamic.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Class OfficeLocationService.
 *
 * @author Wim Van den Brande
 * @since 03/10/2015 - 20:35
 */
@Service
public class OfficeLocationServiceImpl implements OfficeLocationService {
    private static final Logger LOG = LoggerFactory.getLogger(OfficeLocationService.class);
    private String street;
    private String commune;
    private String country;

    public OfficeLocationServiceImpl() {
        this.street = "Interleuvenlaan 17A";
        this.commune = "Heverlee";
        this.country = "Belgium";
    }

    @Override
    public String getStreet() {
        return street;
    }

    @Override
    public String getCommune() {
        return commune;
    }

    @Override
    public String getCountry() {
        return country;
    }

}
