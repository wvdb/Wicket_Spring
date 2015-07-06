package be.ictdynamic.ui.dummy;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class HelloWorld extends WebPage {
    public HelloWorld() {
        add(new Label("message", "Contract 3 ... here we come"));
    }
}