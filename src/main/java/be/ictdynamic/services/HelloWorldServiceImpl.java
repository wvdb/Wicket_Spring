package be.ictdynamic.services;

import org.springframework.stereotype.Service;

/**
 * Class HelloWorldService.
 *
 * @author Wim Van den Brande
 * @since 03/10/2015 - 20:35
 */
@Service
public class HelloWorldServiceImpl implements HelloWorldService {
    String message = "this is the default value";

    public HelloWorldServiceImpl() {
        this.message = "bericht van de service-constructor";
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
}
