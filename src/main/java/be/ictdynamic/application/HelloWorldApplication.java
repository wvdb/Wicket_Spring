package be.ictdynamic.application;

import be.ictdynamic.ui.FirstPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

public class HelloWorldApplication extends WebApplication {
    public HelloWorldApplication() {
    }

    @Override
    public Class getHomePage() {
        return FirstPage.class;
    }

    @Override
    protected void init() {
        super.init();
        // getComponentInitializationListeners().add((IComponentInitializationListener) new SpringComponentInjector(this));
        addComponentInstantiationListener(new SpringComponentInjector(this));
    }
}