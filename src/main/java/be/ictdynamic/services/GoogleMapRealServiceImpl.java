package be.ictdynamic.services;

import be.ictdynamic.domain.GoogleMapRequest;
import be.ictdynamic.domain.GoogleMapResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
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
public class GoogleMapRealServiceImpl implements GoogleMapService {
    private static final Logger LOG = LoggerFactory.getLogger(GoogleMapRealServiceImpl.class);

    public GoogleMapResponse getGoogleDistance(final GoogleMapRequest googleMapRequest) throws Exception {
        HttpClient client = new DefaultHttpClient();

        // HttpGet request = new HttpGet("https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA");
        String httpRequest = "https://maps.googleapis.com/maps/api/geocode/json?address=" + googleMapRequest.getStreet() + ",+" + googleMapRequest.getCommune() + ",+" + googleMapRequest.getCountry();
        HttpGet request = new HttpGet(httpRequest);

        GoogleMapResponse googleMapResponse = new GoogleMapResponse();

        try {
            HttpResponse response = client.execute(request);
            LOG.debug(">>>HTTP response = {}", response);

            // CONVERT RESPONSE TO STRING
            String stringResult = EntityUtils.toString(response.getEntity());
            LOG.debug(">>>HTTP stringResult = {}", stringResult);

            JSONObject jsonobject1 = new JSONObject(stringResult);
            LOG.debug(">>>HTTP jsonobject1 = {}", jsonobject1);

            // CONVERT STRING TO JSON ARRAY
            JSONArray jsonArray = jsonobject1.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {
                // GET INDIVIDUAL JSON OBJECT FROM JSON ARRAY
                JSONObject jsonobject2 = jsonArray.getJSONObject(i);

                LOG.debug(">>>location.lat = " + jsonobject2.getJSONObject("geometry").getJSONObject("location").get("lat"));
                LOG.debug(">>>location.lng = " + jsonobject2.getJSONObject("geometry").getJSONObject("location").get("lng"));

                googleMapResponse.setLat((Double) jsonobject2.getJSONObject("geometry").getJSONObject("location").get("lat"));
                googleMapResponse.setLng((Double) jsonobject2.getJSONObject("geometry").getJSONObject("location").get("lng"));
            }

            return googleMapResponse;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
