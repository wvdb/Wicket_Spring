package be.ictdynamic.services;

import be.ictdynamic.domain.GoogleMapRequest;
import be.ictdynamic.domain.GoogleMapResponse;

/**
 * Class GoogleMapService.
 *
 * @author Wim Van den Brande
 * @since 05/10/2015 - 20:35
 */
public interface GoogleMapService {
    GoogleMapResponse getGoogleLocation(GoogleMapRequest googleMapRequest) throws Exception;
}
