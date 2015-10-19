package be.ictdynamic.services;

//import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.services.bigquery.Bigquery;
//import com.google.api.services.bigquery.BigqueryScopes;
//import com.google.api.services.bigquery.model.GetQueryResultsResponse;
//import com.google.api.services.bigquery.model.QueryRequest;
//import com.google.api.services.bigquery.model.QueryResponse;
//import com.google.api.services.bigquery.model.TableCell;
//import com.google.api.services.bigquery.model.TableRow;

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
public class GoogleBigQueryServiceImpl implements GoogleBigQueryService {
    private static final Logger LOG = LoggerFactory.getLogger(GoogleBigQueryServiceImpl.class);

    public String getWikipediaData() {
        return "";
    }


//
//    public static Bigquery createAuthorizedClient() throws IOException {
//      // Create the credential
//      HttpTransport transport = new NetHttpTransport();
//      JsonFactory jsonFactory = new JacksonFactory();
//      GoogleCredential credential = GoogleCredential.getApplicationDefault(transport, jsonFactory);
//
//      // Depending on the environment that provides the default credentials (e.g. Compute Engine, App
//      // Engine), the credentials may require us to specify the scopes we need explicitly.
//      // Check for this case, and inject the Bigquery scope if required.
//      if (credential.createScopedRequired()) {
//        credential = credential.createScoped(BigqueryScopes.all());
//      }
//
//      return new Bigquery.Builder(transport, jsonFactory, credential)
//          .setApplicationName("Bigquery Samples").build();
//    }
}
