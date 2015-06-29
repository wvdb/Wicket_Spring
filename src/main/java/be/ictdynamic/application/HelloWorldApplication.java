package be.ictdynamic.application;

import be.ictdynamic.ui.GuestBook;
import org.apache.wicket.protocol.http.WebApplication;

public class HelloWorldApplication extends WebApplication {
    public HelloWorldApplication() {
    }

//    @Override
//    public Class getHomePage() {
//        return HelloWorld.class;
//    }

    @Override
    public Class getHomePage() {
        return GuestBook.class;
    }
}