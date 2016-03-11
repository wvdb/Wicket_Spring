package be.ictdynamic.services;

import be.ictdynamic.Constants;
import be.ictdynamic.domain.GoogleMapRequest;
import be.ictdynamic.domain.GoogleMapResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Class GoogleMapDummyServiceImpl.
 *
 * @author Wim Van den Brande
 * @since 03/10/2015 - 20:35
 */
@Service
public class GoogleMapDummyServiceImpl implements GoogleMapService {
    private static final Logger LOG = LoggerFactory.getLogger(GoogleMapDummyServiceImpl.class);
    private String dummy = "dummy value";

    // HttpGet request = new HttpGet("https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA");

    public GoogleMapResponse getGoogleLocation(final GoogleMapRequest googleMapRequest) throws Exception {
        try {
            String stringResult = Constants.DUMMY_RESPONSE;
            JSONObject jsonobject1 = new JSONObject(stringResult);
            JSONArray jsonArray = jsonobject1.getJSONArray("results");
            GoogleMapResponse googleMapResponse = new GoogleMapResponse();

            for (int i = 0; i < jsonArray.length(); i++) {
                // GET INDIVIDUAL JSON OBJECT FROM JSON ARRAY
                JSONObject jsonobject2 = jsonArray.getJSONObject(i);

                LOG.debug(">>location.lat = " + jsonobject2.getJSONObject("geometry").getJSONObject("location").get("lat"));
                LOG.debug(">>location.lng = " + jsonobject2.getJSONObject("geometry").getJSONObject("location").get("lng"));

                googleMapResponse.setLat((Double) jsonobject2.getJSONObject("geometry").getJSONObject("location").get("lat"));
                googleMapResponse.setLng((Double) jsonobject2.getJSONObject("geometry").getJSONObject("location").get("lng"));
            }
            return googleMapResponse;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public GoogleMapResponse getGoogleDistance(GoogleMapRequest googleMapRequest) throws Exception {
        return null;
    }
}
