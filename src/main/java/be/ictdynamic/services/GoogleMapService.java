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
    /**
     * Method retrieves latitude and longitude of an address
     *
     * @param googleMapRequest the request containing the address
     * @return response
     */
    GoogleMapResponse getGoogleLocation(GoogleMapRequest googleMapRequest) throws Exception;

    /**
     * Method retrieves distance and duration for a pair of addresses
     *
     * @param googleMapRequest the request containing the pair of addresses
     * @return response
     */
    GoogleMapResponse getGoogleDistance(GoogleMapRequest googleMapRequest) throws Exception;
}
