package be.ictdynamic.services;

import be.ictdynamic.Constants;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Class GoogleMapFactoryServiceImpl.
 *
 * @author Wim Van den Brande
 * @since 03/10/2015 - 20:35
 */
@Service
public class GoogleMapFactoryServiceImpl {
    private static final Logger LOG = LoggerFactory.getLogger(GoogleMapFactoryServiceImpl.class);

    @SpringBean
    private GoogleMapRealServiceImpl googleMapRealServiceImpl;

    @SpringBean
    private GoogleMapDummyServiceImpl googleMapDummyServiceImpl;

    public GoogleMapService getGoogleMapService() {
        if (Constants.REAL_GOOGLE_MAP_SERVICE) {
            return getGoogleMapRealService();
        }
        else {
            return getGoogleMapDummyService();
        }
    }

    // lazy loading the GoogleMapRealService

    private GoogleMapRealServiceImpl getGoogleMapRealService() {
        if (googleMapRealServiceImpl == null ) {
            googleMapRealServiceImpl = new GoogleMapRealServiceImpl();
//            googleMapRealServiceImpl = googleMapRealServiceImpl;
        }
        return googleMapRealServiceImpl;
    }

    // lazy loading the GoogleMapDummyService

    private GoogleMapDummyServiceImpl getGoogleMapDummyService() {
        if (googleMapDummyServiceImpl == null ) {
            googleMapDummyServiceImpl = new GoogleMapDummyServiceImpl();
//            googleMapDummyServiceImpl = googleMapDummyServiceImpl;
        }
        return googleMapDummyServiceImpl;
    }

}
