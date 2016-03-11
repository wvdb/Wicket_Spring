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

import java.net.URI;

/**
 * Class GoogleMapRealServiceImpl.
 *
 * @author Wim Van den Brande
 * @since 03/10/2015 - 20:35
 */
@Service
public class GoogleMapRealServiceImpl implements GoogleMapService {
    private static final Logger LOG = LoggerFactory.getLogger(GoogleMapRealServiceImpl.class);

    public GoogleMapResponse getGoogleLocation(final GoogleMapRequest googleMapRequest) throws Exception {
        HttpClient client = new DefaultHttpClient();

        URI uri = new URI(
                "https",
                "maps.googleapis.com",
                "/maps/api/geocode/json",
                "address=" + googleMapRequest.getOfficeStreet() + ",+" + googleMapRequest.getOfficeCommune() + ",+" + googleMapRequest.getOfficeCountry(),
                null);
        String httpRequest = uri.toASCIIString();
        HttpGet request = new HttpGet(httpRequest);

        GoogleMapResponse googleMapResponse = new GoogleMapResponse();

        try {
            HttpResponse response = client.execute(request);
            LOG.debug(">>>HTTP response = {}" + response);

            // CONVERT RESPONSE TO STRING
            String stringResult = EntityUtils.toString(response.getEntity());
            LOG.debug(">>>HTTP stringResult = {}" +  stringResult);

            JSONObject jsonobject1 = new JSONObject(stringResult);
            LOG.debug(">>>HTTP jsonobject1 = {}" +  jsonobject1);

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
            LOG.error(">>>Exception occurred when querying Google Maps: message = " + e.getMessage());
            throw new Exception(e);
        }
    }

    public GoogleMapResponse getGoogleDistance(final GoogleMapRequest googleMapRequest) throws Exception {
        HttpClient client = new DefaultHttpClient();

        URI uri = new URI(
                "https",
                "maps.googleapis.com",
                "/maps/api/distancematrix/json",
                  "origins=" + googleMapRequest.getHomeStreet() + ",+" + googleMapRequest.getHomeCommune() + ",+" + googleMapRequest.getHomeCountry()
                + "&destinations=" + googleMapRequest.getOfficeStreet() + ",+" + googleMapRequest.getOfficeCommune() + ",+" + googleMapRequest.getOfficeCountry(),
                null);
        String httpRequest = uri.toASCIIString();
        HttpGet request = new HttpGet(httpRequest);

        GoogleMapResponse googleMapResponse = new GoogleMapResponse();

        try {
            HttpResponse response = client.execute(request);
            LOG.debug(">>>HTTP response = {}" + response);

            // CONVERT RESPONSE TO STRING
            String stringResult = EntityUtils.toString(response.getEntity());
            LOG.debug(">>>HTTP stringResult = {}" +  stringResult);

            JSONObject jsonobject1 = new JSONObject(stringResult);
            LOG.debug(">>>HTTP jsonobject1 = {}" +  jsonobject1);

            // CONVERT STRING TO JSON ARRAY
            JSONArray jsonArrayRow = jsonobject1.getJSONArray("rows");

            for (int i = 0; i < jsonArrayRow.length(); i++) {
                // GET INDIVIDUAL JSON OBJECT FROM JSON ARRAY
                JSONObject jsonobject2 = jsonArrayRow.getJSONObject(i);

                JSONArray jsonArrayElement = jsonobject2.getJSONArray("elements");
                for (int j = 0; j < jsonArrayElement.length(); j++) {
                    JSONObject jsonElement = jsonArrayElement.getJSONObject(j);
                    LOG.debug(">>>location.lat = " + jsonElement.getJSONObject("distance").get("text"));
                    LOG.debug(">>>location.lng = " + jsonElement.getJSONObject("distance").get("value"));
                    LOG.debug(">>>location.lat = " + jsonElement.getJSONObject("duration").get("text"));
                    LOG.debug(">>>location.lng = " + jsonElement.getJSONObject("duration").get("value"));

                    googleMapResponse.setDistance((Integer) jsonElement.getJSONObject("distance").get("value"));
                    googleMapResponse.setDuration((Integer) jsonElement.getJSONObject("duration").get("value"));
                }

            }

            return googleMapResponse;
        } catch (Exception e) {
            LOG.error(">>>Exception occurred when querying Google Maps: message = " + e.getMessage());
            throw new Exception(e);
        }
    }

}
