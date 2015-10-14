package be.ictdynamic.services;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Class HelloWorldService.
 *
 * @author Wim Van den Brande
 * @since 03/10/2015 - 20:35
 */
@Service
public class HelloWorldServiceImpl implements HelloWorldService {
    String country;
    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldServiceImpl.class);

//    private static DefaultHttpClient httpClient = ConnectionManager.getClient();

    public HelloWorldServiceImpl() {
        this.country = "Belgium";
    }

    @Override
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void getDummy() {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA");

        try {
            HttpResponse response = client.execute(request);
            LOG.debug(">>>HTTP response = {}", response);

            // CONVERT RESPONSE TO STRING
            String result = EntityUtils.toString(response.getEntity());

            // CONVERT STRING TO JSON ARRAY
//            JSONArray jsonArray = new JSONArray(result);
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
