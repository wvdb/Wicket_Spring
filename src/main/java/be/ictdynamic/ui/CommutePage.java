package be.ictdynamic.ui;

import be.ictdynamic.domain.Commuter;
import be.ictdynamic.services.GoogleMapService;
import be.ictdynamic.services.OfficeLocationService;
import be.ictdynamic.ui.base.BasePage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CommutePage extends BasePage {
    private static final Logger LOG = LoggerFactory.getLogger(CommutePage.class);

    @SpringBean
    private OfficeLocationService officeLocationService;

    @SpringBean
    private GoogleMapService googleMapService;

    /**
     * Constructor that is invoked when page is invoked without a session.
     */
    public CommutePage() {
        initGui();
    }

    private void initGui() {

        // bind Form with Model of Type CompoundProperty !!!
        Form<CommutePage> form = new Form<CommutePage>("commutePageForm", new CompoundPropertyModel<CommutePage>(new Commuter()));

        add(form);

        // location of office is retrieved by using a (not very intelligent) service

        TextField<String> officeStreetField = new TextField<String>("officeStreet");
        TextField<String> officeCommuneField = new TextField<String>("officeCommune");
        TextField<String> officeCountryField = new TextField<String>("officeCountry");

        if (officeLocationService == null) {
            LOG.warn(">>>This is not supposed to happen");
            officeCountryField.setModel(Model.of("Spring is not always fun"));
        } else {
            LOG.debug(">>>Spring config ok .... ready to roll");
            officeStreetField.setModel(Model.of(officeLocationService.getStreet()));
            officeCountryField.setModel(Model.of(officeLocationService.getCountry()));
            officeCommuneField.setModel(Model.of(officeLocationService.getCommune()));
        }

        // location of commuter's home address is retrieved by using the Commuter POJO class (being mapped as a Model)

        TextField homeStreetField = new TextField("homeStreet");
        TextField homeCommuneField = new TextField("homeCommune");
        TextField homeCountryField = new TextField("homeCountry");

        Button submitButton = new Button("submitButton") {
            private static final long serialVersionUID = 1858351845188795071L;

            @Override
            public void onSubmit() {

                // invoke actual service
                String response = googleMapService.getGoogleDistance();

                PageParameters pageParameters = new PageParameters();
                pageParameters.add("reponseGoogleMap", response);

                // setResponsePage(new ResponsePage());
                // setResponsePage(ResponsePage.class, pageParameters);
                setResponsePage(new ResponsePage(response));
            }
        };

        form.add(officeStreetField).setVersioned(false);
        form.add(officeCommuneField).setVersioned(false);
        form.add(officeCountryField).setVersioned(false);

        form.add(homeStreetField).setVersioned(false);
        form.add(homeCommuneField).setVersioned(false);
        form.add(homeCountryField).setVersioned(false);

        form.add(submitButton);


    }

}
