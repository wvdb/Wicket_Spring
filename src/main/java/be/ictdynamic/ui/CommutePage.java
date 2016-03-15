package be.ictdynamic.ui;

import be.ictdynamic.Constants;
import be.ictdynamic.domain.Commuter;
import be.ictdynamic.domain.GoogleMapRequest;
import be.ictdynamic.domain.GoogleMapResponse;
import be.ictdynamic.services.DummyService;
import be.ictdynamic.services.GoogleMapFactoryServiceImpl;
import be.ictdynamic.services.OfficeLocationService;
import be.ictdynamic.services.SecurityService;
import be.ictdynamic.ui.base.BasePage;
import org.apache.commons.collections4.Predicate;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public final class CommutePage extends BasePage {
    private static final Logger LOG = LoggerFactory.getLogger(CommutePage.class);
    private static final long serialVersionUID = -264755820748811049L;
    private static final List<String> HOST_TYPES = Arrays.asList("Shared Host", "VPS", "Dedicated Server");

    private String selected1 = "Shared Host";
    private String selected2 = "VPS";

    @SpringBean
    private OfficeLocationService officeLocationService;

    @SpringBean
    private GoogleMapFactoryServiceImpl googleMapFactoryService;

    @SpringBean
    private SecurityService securityService;

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
        final Commuter commuter = new Commuter();

        Form<Commuter> form = new Form<>("commutePageForm", new CompoundPropertyModel<>(Model.of(commuter)));

        add(form);

        // location of office is retrieved by using a (not very intelligent) service

        final TextField<String> officeStreetField = new TextField<>("officeStreet");
        final TextField<String> officeCommuneField = new TextField<>("officeCommune");
        final TextField<String> officeCountryField = new TextField<>("officeCountry");

        if (officeLocationService == null) {
            LOG.warn(">>>This is not supposed to happen");
            officeCountryField.setModel(Model.of("Spring is not always fun"));
        } else {
            LOG.debug(">>>Spring config ok .... ready to roll");
            officeStreetField.setModel(Model.of(officeLocationService.getStreet()));
            officeCommuneField.setModel(Model.of(officeLocationService.getCommune()));
            officeCountryField.setModel(Model.of(officeLocationService.getCountry()));
        }

        // example of radio button
        // -----------------------

        RadioChoice<String> hostingType = new RadioChoice<>(
                "hostingTypes", new PropertyModel<String>(this, "selected1"), HOST_TYPES);

        form.add(hostingType);

        // example of customized radio button
        // -----------------------

        WimRadioChoice<String> hostingType2 = new WimRadioChoice<>(
                "hostingTypes2", new PropertyModel<String>(this, "selected2"), HOST_TYPES);

        form.add(hostingType2);

        // location of commuter's home address is based on Commuter and CompoundPropertyModel
        // ----------------------------------------------------------------------------------

        TextField<String> homeStreetField = new TextField<>("homeStreet");
        TextField<String> homeCommuneField = new TextField<>("homeCommune");
        final TextField<String> homeCountryField = new TextField<>("homeCountry");

        // example of dynamic behavior
        // ---------------------------
        WebMarkupContainer webMarkupContainer = new WebMarkupContainer("validationCorrectContainer");

        TextField<String> dummyField = new TextField<>("dummy");
        webMarkupContainer.add(dummyField);

        ValidationCorrectContainerPredicate validationCorrectContainerPredicate = new ValidationCorrectContainerPredicate();
        webMarkupContainer.add(new VisibilityBehavior(validationCorrectContainerPredicate));
        webMarkupContainer.setOutputMarkupId(true);
        webMarkupContainer.setOutputMarkupPlaceholderTag(true);
        form.add(webMarkupContainer);

        // example of validation

        form.add(officeStreetField.setRequired(true)).setVersioned(false);
        form.add(officeCommuneField.setRequired(true)).setVersioned(false);
        form.add(officeCountryField.setRequired(true)).setVersioned(false);

        form.add(homeStreetField.add(StringValidator.lengthBetween(Constants.MIN_LENGTH, Constants.MAX_LENGTH_STREET))).setVersioned(false);
        form.add(homeCommuneField.add(StringValidator.lengthBetween(Constants.MIN_LENGTH, Constants.MAX_LENGTH_COMMUNE))).setVersioned(false);
        form.add(homeCountryField.add(StringValidator.lengthBetween(Constants.MIN_LENGTH, Constants.MAX_LENGTH_COUNTRY))).setVersioned(false);

        // button with wicket id submitButton and with some basic logic

        Button submitButton = new Button("submitButton") {
            private static final long serialVersionUID = 1858351845188795071L;

            // dummy usage of securityService (component wil be visible when condition is met)
            @Override
            protected void onConfigure() {
                super.onInitialize();
                setVisibilityAllowed(securityService.isButtonAllowed("CommutePage"));
            }

            // dummy usage of securityService (component wil be visible when condition is met)
            @Override
            public boolean isVisible() {
                return securityService.isButtonAllowed("CommutePage");
            }

            @Override
            public void onSubmit() {

                // dummy logging for radio button 1
                info("Selected Type : " + selected1);
                LOG.info(">>>Selected Type: " + selected1);
                LOG.info(">>>HomeCountryField: " + homeCountryField.getDefaultModelObjectAsString());

                // invoke factory instantiating a Google Map Service
                // http://www.tutorialspoint.com/design_pattern/factory_pattern.htm

                GoogleMapRequest googleMapRequest = new GoogleMapRequest();
                googleMapRequest.setOfficeStreet(officeStreetField.getDefaultModelObjectAsString());
                googleMapRequest.setOfficeCommune(officeCommuneField.getDefaultModelObjectAsString());
                googleMapRequest.setOfficeCountry(officeCountryField.getDefaultModelObjectAsString());

                googleMapRequest.setHomeStreet(((Commuter) findParent(Form.class).getDefaultModelObject()).getHomeStreet());
                googleMapRequest.setHomeCommune(((Commuter) findParent(Form.class).getDefaultModelObject()).getHomeCommune());
                googleMapRequest.setHomeCountry(((Commuter) findParent(Form.class).getDefaultModelObject()).getHomeCountry());

                GoogleMapResponse googleMapResponse = null;
                try {
//                    googleMapResponse = googleMapFactoryService.getGoogleMapService().getGoogleLocation(googleMapRequest);
                    googleMapResponse = googleMapFactoryService.getGoogleMapService().getGoogleDistance(googleMapRequest);
                } catch (Exception e) {
                    LOG.error(">>>GoogleMapService is not available: exception = " + e);
                    error("GoogleMapService is not available");
                }

                // improper support for navigating from CommutePage to ResponseLocationPage
                // PageParameters pageParameters = new PageParameters();
                // pageParameters.add("reponseGoogleMap", response);

                // to support navigation from CommutePage to ResponseLocationPage
                setResponsePage(new ResponseLocationPage(googleMapResponse));
            }
        };

        form.add(submitButton);
    }

    public Commuter getCommuter() {
        return (Commuter) this.getDefaultModelObject();
    }

    public void handleRadioButtonUpdate(AjaxRequestTarget target) {
//        ((Commuter) get("commutePageForm").getDefaultModelObject()).setHomeCountry("dit is een test");
        target.add(get("commutePageForm").get("validationCorrectContainer"));
    }

    private static class ValidationCorrectContainerPredicate implements Predicate<Component>, Serializable {
        private static final long serialVersionUID = 7719059818782432234L;

        // condition is met when hostingtype is equal to Dedicated Server
        @Override
        public boolean evaluate(Component component) {
            return component.getParent().get("hostingTypes2").getDefaultModelObjectAsString().equals("Dedicated Server");
        }
    }


}
