package be.ictdynamic.services;

import org.springframework.stereotype.Service;

/**
 * Class HelloWorldService.
 *
 * @author Wim Van den Brande
 * @since 03/10/2015 - 20:35
 */
@Service
public class HelloWorldService {
    String message = "this is the default value";

    public HelloWorldService() {
        this.message = "bericht in de constructor";
    }

    public String getMessage() {
        return "this is an empty string";
    }

    public void setMessage(String message) {
        this.message = "this is a test";
    }
}
