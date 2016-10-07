package be.ictdynamic.application;

import be.ictdynamic.ui.CommutePage;
import de.agilecoders.wicket.core.Bootstrap;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

public class HelloWorldApplication extends WebApplication {
    public HelloWorldApplication() {
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return CommutePage.class;
    }

    @Override
    protected void init() {
        super.init();
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));

        Bootstrap.install(this);
    }
}