package be.ictdynamic.services;

import be.ictdynamic.common.DateUtilities;
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
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class GoogleMapRealServiceImpl.
 *
 * @author Wim Van den Brande
 * @since 03/10/2015 - 20:35
 */
@Service
public class GoogleMapRealServiceImpl implements GoogleMapService {
    private static final Logger LOG = LoggerFactory.getLogger(GoogleMapRealServiceImpl.class);
    public static final String GOOGLE_DISTANCE_MATRIX_API_KEY = "AIzaSyDrQxf6ftnF-2xihZBUQkTL6ZEIlgee5WA";

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
            LOG.debug(">>>HTTP stringResult = {}" + stringResult);

            JSONObject jsonobject1 = new JSONObject(stringResult);
            LOG.debug(">>>HTTP jsonobject1 = {}" + jsonobject1);

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

    public GoogleMapResponse getGoogleDistanceForEntireWeek(final GoogleMapRequest googleMapRequest) throws Exception {
        long firstDayOfWeekinSeconds = DateUtilities.getFirstDayOfWeek();

        GoogleMapResponse googleMapResponse = new GoogleMapResponse();
        Collection<GoogleMapResponse.Voyage> voyages = new ArrayList<>(14);

        // add 8 hours
        firstDayOfWeekinSeconds += 8 * 3600000;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        // start from 8 AM and increase team in slots of 12 hours
        for (int i = 1; i <= 14; i++) {
            GoogleMapResponse.Voyage voyage = getGoogleDistanceBasedOnTime(googleMapRequest, firstDayOfWeekinSeconds);
            voyages.add(voyage);
            // add 12 hours hours
            firstDayOfWeekinSeconds += 12 * 3600000;

            LOG.info(">>>Google info retrieved for time " + dateFormat.format(voyage.getVoyageStartTime().getTime()) + ": duration = " + voyage.getVoyageDuration() + ", distance = " + voyage.getVoyageDistance());
        }

        googleMapResponse.setVoyages(voyages);

        Comparator<GoogleMapResponse.Voyage> voyageComparator = new Comparator<GoogleMapResponse.Voyage>() {
            @Override
            public int compare(GoogleMapResponse.Voyage o1, GoogleMapResponse.Voyage o2) {
                return o1.getVoyageDuration().compareTo(o2.getVoyageDuration());
            }
        };

        GoogleMapResponse.Voyage fastVoyage = Collections.max(googleMapResponse.getVoyages(), voyageComparator);
        GoogleMapResponse.Voyage slowVoyage = Collections.min(googleMapResponse.getVoyages(), voyageComparator);

        LOG.info("Voyage taking the most time : " + fastVoyage.getVoyageDuration() + " planned on " + dateFormat.format(fastVoyage.getVoyageStartTime().getTime()));
        LOG.info("Voyage taking the least time : " + slowVoyage.getVoyageDuration() + " planned on " + dateFormat.format(slowVoyage.getVoyageStartTime().getTime()));

        return googleMapResponse;
    }

    public GoogleMapResponse getGoogleDistance(final GoogleMapRequest googleMapRequest) throws Exception {
        // TODO : to fix time issue
        long now = System.currentTimeMillis() % 1000;

        GoogleMapResponse googleMapResponse = new GoogleMapResponse();
        Collection<GoogleMapResponse.Voyage> voyages = new ArrayList<>(1);
        GoogleMapResponse.Voyage voyage = getGoogleDistanceBasedOnTime(googleMapRequest, now);
        voyages.add(voyage);
        googleMapResponse.setVoyages(voyages);

        return googleMapResponse;
    }

    public GoogleMapResponse.Voyage getGoogleDistanceBasedOnTime(final GoogleMapRequest googleMapRequest, long startTime) throws Exception {
        HttpClient client = new DefaultHttpClient();

        // traffic_model = pessimistic/optimistic can be added

        URI uri = new URI(
                "https",
                "maps.googleapis.com",
                "/maps/api/distancematrix/json",
                "origins=" + googleMapRequest.getHomeStreet() + ",+" + googleMapRequest.getHomeCommune() + ",+" + googleMapRequest.getHomeCountry()
                        + "&destinations=" + googleMapRequest.getOfficeStreet() + ",+" + googleMapRequest.getOfficeCommune() + ",+" + googleMapRequest.getOfficeCountry()
                        + "&key=" + GOOGLE_DISTANCE_MATRIX_API_KEY,
//                        + "&departure_time=" + startTime + "&key=" + GOOGLE_DISTANCE_MATRIX_API_KEY,
                null);

        String httpRequest = uri.toASCIIString();

        LOG.debug(">>>HTTP request = {}" + httpRequest);

        HttpGet request = new HttpGet(httpRequest);
        GoogleMapResponse.Voyage voyage = new GoogleMapResponse.Voyage();

        try {
            HttpResponse response = client.execute(request);
            LOG.debug(">>>HTTP response = {}" + response);

            // CONVERT RESPONSE TO STRING
            String stringResult = EntityUtils.toString(response.getEntity());
            LOG.debug(">>>HTTP stringResult = {}" + stringResult);

            JSONObject jsonobject1 = new JSONObject(stringResult);
            LOG.debug(">>>HTTP jsonobject1 = {}" + jsonobject1);

            // CONVERT STRING TO JSON ARRAY
            JSONArray jsonArrayRow = jsonobject1.getJSONArray("rows");

            for (int i = 0; i < jsonArrayRow.length(); i++) {
                // GET INDIVIDUAL JSON OBJECT FROM JSON ARRAY
                JSONObject jsonobject2 = jsonArrayRow.getJSONObject(i);

                JSONArray jsonArrayElement = jsonobject2.getJSONArray("elements");
                for (int j = 0; j < jsonArrayElement.length(); j++) {
                    JSONObject jsonElement = jsonArrayElement.getJSONObject(j);
                    LOG.debug(String.valueOf(">>>distance = " + jsonElement.opt("distance") == null ? 0 : (Integer) jsonElement.getJSONObject("distance").get("value")));
                    LOG.debug(String.valueOf(">>>duration = " + jsonElement.opt("duration") == null ? 0 : (Integer) jsonElement.getJSONObject("duration").get("value")));

                    voyage.setVoyageDistance(jsonElement.opt("distance") == null ? 0 : (Integer) jsonElement.getJSONObject("distance").get("value"));
//                    voyage.setVoyageDuration((Integer) jsonElement.getJSONObject("duration_in_traffic").get("value"));
                    voyage.setVoyageDuration(jsonElement.opt("duration") == null ? 0 : (Integer) jsonElement.getJSONObject("duration").get("value"));

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(startTime);
                    voyage.setVoyageStartTime(calendar);
                }

            }

        } catch (Exception e) {
            LOG.error(">>>Exception occurred when querying Google Maps: message = " + e.getMessage());
            throw new Exception(e);
        }

        return voyage;
    }

}
