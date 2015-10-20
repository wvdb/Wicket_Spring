package be.ictdynamic.services;

import be.ictdynamic.Constants;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class GoogleMapDummyServiceImpl.
 *
 * @author Wim Van den Brande
 * @since 03/10/2015 - 20:35
 */
public class GoogleMapDummyServiceImpl implements GoogleMapService {
    private static final Logger LOG = LoggerFactory.getLogger(GoogleMapDummyServiceImpl.class);

    public String getGoogleDistance() {
        try {
            String stringResult = Constants.DUMMY_RESPONSE;
            JSONObject jsonobject1 = new JSONObject(stringResult);
            JSONArray jsonArray = jsonobject1.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {
                // GET INDIVIDUAL JSON OBJECT FROM JSON ARRAY
                JSONObject jsonobject2 = jsonArray.getJSONObject(i);

                LOG.debug(">>>location.lat = " + jsonobject2.getJSONObject("geometry").getJSONObject("location").get("lat"));
                LOG.debug(">>>location.lng = " + jsonobject2.getJSONObject("geometry").getJSONObject("location").get("lng"));
            }

            return "dummy";

        } catch (Exception e) {
            LOG.error(">>>GoogleMapRealServiceImpl : Error message = " + e.getMessage());
            throw new IllegalArgumentException("Google not available");
        }
    }
}
