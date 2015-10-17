package be.ictdynamic.services;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.wicket.ajax.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Class GoogleMapServiceImpl.
 *
 * @author Wim Van den Brande
 * @since 03/10/2015 - 20:35
 */
@Service
public class GoogleMapServiceImpl implements GoogleMapService {
    private static final Logger LOG = LoggerFactory.getLogger(GoogleMapServiceImpl.class);

    public void getDummy() {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA");

        try {
            HttpResponse response = client.execute(request);
            LOG.debug(">>>HTTP response = {}", response);

            // CONVERT RESPONSE TO STRING
            String stringResult = EntityUtils.toString(response.getEntity());
            LOG.debug(">>>HTTP stringResult = {}", stringResult);

            JSONObject jsonobject1 = new JSONObject(stringResult);
            LOG.debug(">>>HTTP jsonobject1 = {}", jsonobject1);

            String results = jsonobject1.getString("results");
            LOG.debug(">>>Google Map results = {}", results);

            JSONObject jsonobject2 = new JSONObject(results);
            String place_id = jsonobject2.getString("place_id");
            LOG.debug(">>>HTTP place_id = {}", place_id);

            // CONVERT STRING TO JSON ARRAY
            // JSONArray jsonArray = new JSONArray(result);
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//                // GET INDIVIDUAL JSON OBJECT FROM JSON ARRAY
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                LOG.debug(">>> >>> jsonObject = {}", jsonObject);
//            }

        } catch (Exception e) {
            LOG.error(">>>Error message = " + e.getMessage());
        }
    }
}
