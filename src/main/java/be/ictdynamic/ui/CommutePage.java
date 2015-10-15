package be.ictdynamic.ui;

import be.ictdynamic.domain.Commuter;
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

        // bind Model with Page !!!
        Form<CommutePage> form = new Form<CommutePage>("commutePageForm", new CompoundPropertyModel<CommutePage>(new Commuter()));

        add(form);

        TextField<String> officeStreetField = new TextField<String>("officeStreet");
        TextField<String> officeCommuneField = new TextField<String>("officeCommune");
        TextField<String> officeCountryField = new TextField<String>("officeCountry");

        TextField homeStreetField = new TextField("homeStreet");
        TextField homeCommuneField = new TextField("homeCommune");
        TextField homeCountryField = new TextField("homeCountry");

        if (helloWorldService == null) {
            LOG.warn(">>>This is not supposed to happen");
            officeCountryField.setModel(Model.of("Spring is not always fun"));
        } else {
            LOG.debug(">>>Spring config ok .... ready to roll");
            officeCountryField.setModel(Model.of(helloWorldService.getCountry()));
            officeCommuneField.setModel(Model.of(helloWorldService.getCommune()));
        }

        Button submitButton = new Button("submitButton") {
            private static final long serialVersionUID = 1858351845188795071L;

            @Override
            public void onSubmit() {
                setResponsePage(AddCommentPage.class);
            }
        };

        form.add(officeStreetField).setVersioned(false);
        form.add(officeCommuneField).setVersioned(false);
        form.add(officeCountryField).setVersioned(false);

        form.add(homeStreetField).setVersioned(false);
        form.add(homeCommuneField).setVersioned(false);
        form.add(homeCountryField).setVersioned(false);

        form.add(submitButton);

        // invoke actual service
        helloWorldService.getDummy();

    }

}
