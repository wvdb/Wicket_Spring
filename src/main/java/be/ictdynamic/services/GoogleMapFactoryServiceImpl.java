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

    public GoogleMapFactoryServiceImpl () {
//        InjectorHolder.getInjector().inject(this);
    }

    @SpringBean
    private GoogleMapRealServiceImpl googleMapRealServiceImpl;

    @SpringBean
    private GoogleMapDummyServiceImpl googleMapDummyServiceImpl;

    @SpringBean
    private DummyService dummyService;

    public GoogleMapService getGoogleMapService() {
        if (Constants.REAL_GOOGLE_MAP_SERVICE) {
//            return getGoogleMapRealService();
            return new GoogleMapRealServiceImpl();
        }
        else {
//            return getGoogleMapDummyService();
            return new GoogleMapDummyServiceImpl();
        }
    }

    private GoogleMapRealServiceImpl getGoogleMapRealService() {
        return googleMapRealServiceImpl;
    }

    private GoogleMapDummyServiceImpl getGoogleMapDummyService() {
        return googleMapDummyServiceImpl;
    }

//    @Autowired
//    private void setDummyService(DummyService dummyService) {
//        this.dummyService = dummyService;
//    }
}
