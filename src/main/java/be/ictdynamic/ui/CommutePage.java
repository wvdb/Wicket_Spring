package be.ictdynamic.ui;

import be.ictdynamic.services.HelloWorldService;
import be.ictdynamic.ui.base.BasePage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CommutePage extends BasePage {
    public String street;
    public String commune;
    public String country;

    private static final Logger LOG = LoggerFactory.getLogger(CommutePage.class);

    @SpringBean
    private HelloWorldService helloWorldService;

    /**
     * Constructor that is invoked when page is invoked without a session.
     */
    public CommutePage() {
        initGui();
    }

    private void initGui() {

        Form<CommutePage> form = new Form<CommutePage>("commutePageForm", new CompoundPropertyModel<CommutePage>(this));
        add(form);

        TextField street = new TextField("street");
        form.add(street).setVersioned(false);

        TextField commune = new TextField("commune");
        form.add(commune).setVersioned(false);

        TextField country = new TextField("country");
        if (helloWorldService == null) {
            LOG.warn(">>>This is not supposed to happen");
            country.setModel(Model.of("Spring is not always fun"));
        } else {
            LOG.debug(">>>Spring config ok .... ready to roll");
            country.setModel(Model.of(helloWorldService.getCountry()));
        }
        form.add(country).setVersioned(false);

        Button submitButton = new Button("submitButton") {
            @Override
            public void onSubmit() {
                setResponsePage(AddCommentPage.class);
            }
        };

        form.add(submitButton);

    }

}
