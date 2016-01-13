package be.ictdynamic.ui;

import be.ictdynamic.Constants;
import be.ictdynamic.domain.Commuter;
import be.ictdynamic.domain.GoogleMapRequest;
import be.ictdynamic.domain.GoogleMapResponse;
import be.ictdynamic.services.DummyService;
import be.ictdynamic.services.GoogleMapFactoryServiceImpl;
import be.ictdynamic.services.OfficeLocationService;
import be.ictdynamic.ui.base.BasePage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class CommutePage extends BasePage {
    private static final Logger LOG = LoggerFactory.getLogger(CommutePage.class);

    @SpringBean
    private OfficeLocationService officeLocationService;

    @SpringBean
    private GoogleMapFactoryServiceImpl googleMapFactoryService;

    @SpringBean
    private DummyService dummyService;

    /**
     * Constructor that is invoked when page is invoked without a session.
     */
    public CommutePage() {
        initGui();
    }

    private void initGui() {

        // bind Form with Model of Type CompoundProperty !!!
        // -------------------------------------------------

        Commuter commuter = new Commuter();
        Form<CommutePage> form = new Form<CommutePage>("commutePageForm", new CompoundPropertyModel<CommutePage>(commuter));

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

                // invoke factory instantiating a Google Map Service
                // http://www.tutorialspoint.com/design_pattern/factory_pattern.htm

                GoogleMapRequest googleMapRequest = new GoogleMapRequest();
                googleMapRequest.setStreet(officeLocationService.getStreet());
                googleMapRequest.setCommune(officeLocationService.getCommune());
                googleMapRequest.setCountry(officeLocationService.getCountry());

                GoogleMapResponse googleMapResponse = null;
                try {
                    googleMapResponse = googleMapFactoryService.getGoogleMapService().getGoogleDistance(googleMapRequest);
                } catch (Exception e) {
                    error("GoogleMapService is not available");
                }

//                PageParameters pageParameters = new PageParameters();
//                pageParameters.add("reponseGoogleMap", response);

                setResponsePage(new ResponsePage(googleMapResponse));
            }
        };

        form.add(officeStreetField.setRequired(true)).setVersioned(false);
        form.add(officeCommuneField.setRequired(true)).setVersioned(false);
        form.add(officeCountryField.setRequired(true)).setVersioned(false);

        form.add(homeStreetField.add(StringValidator.lengthBetween(Constants.MIN_LENGTH, Constants.MAX_LENGTH_STREET))).setVersioned(false);
        form.add(homeCommuneField.add(StringValidator.lengthBetween(Constants.MIN_LENGTH, Constants.MAX_LENGTH_COMMUNE))).setVersioned(false);
        form.add(homeCountryField.add(StringValidator.lengthBetween(Constants.MIN_LENGTH, Constants.MAX_LENGTH_COUNTRY))).setVersioned(false);

        form.add(submitButton);

        dummyJXLS();
    }

    private void dummyJXLS() {
        InputStream is = CommutePage.class.getResourceAsStream("c:\\temp\\jxls\\test1.xls");

        try {
            OutputStream os = new FileOutputStream("c:\\temp\\jxls\\test2.xls");
            Context context = new Context();
            context.putVar("test", "dit is een test");
            JxlsHelper.getInstance().processTemplate(is, os, context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
