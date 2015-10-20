package be.ictdynamic.services;

import be.ictdynamic.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Class GoogleMapRealServiceImpl.
 *
 * @author Wim Van den Brande
 * @since 03/10/2015 - 20:35
 */
@Service
public class GoogleMapFactoryServiceImpl {
    private static final Logger LOG = LoggerFactory.getLogger(GoogleMapFactoryServiceImpl.class);

    public GoogleMapService getGoogleMapService() {
        if (Constants.REAL_GOOGLE_MAP_SERVICE) {
            return new GoogleMapRealServiceImpl();
        }
        else {
            return new GoogleMapDummyServiceImpl();
        }
    }
}
