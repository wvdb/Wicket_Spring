package be.ictdynamic.ui;

import be.ictdynamic.common.collections.CollectionUtilities;
import be.ictdynamic.domain.Commuter;
import be.ictdynamic.domain.GoogleMapRequest;
import be.ictdynamic.domain.GoogleMapResponse;
import be.ictdynamic.services.GoogleMapFactoryServiceImpl;
import be.ictdynamic.ui.base.BasePage;
import be.ictdynamic.ui.base.BaseTextField;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CommutePage extends BasePage {
    private static final Logger LOG = LoggerFactory.getLogger(CommutePage.class);
    private static final long serialVersionUID = -264755820748811049L;

    @SpringBean
    @SuppressWarnings({"UnusedDeclaration"})
    private GoogleMapFactoryServiceImpl googleMapFactoryService;

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

        this.add(form);

        // OFFICE FIELDS

        BaseTextField officeStreetField = new BaseTextField("officeStreet");
        BaseTextField officeCommuneField = new BaseTextField("officeCommune");
        BaseTextField officeCountryField = new BaseTextField("officeCountry");

        // HOME FIELDS
        // -----------

        BaseTextField homeStreetField = new BaseTextField("homeStreet");
        BaseTextField homeCommuneField = new BaseTextField("homeCommune");
        BaseTextField homeCountryField = new BaseTextField("homeCountry");

        // example of validation

        form.add(new Label("distance").setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true));
        form.add(new Label("duration").setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true));

        form.add(officeStreetField.setRequired(true)).setVersioned(false);
        form.add(officeCommuneField.setRequired(true)).setVersioned(false);
        form.add(officeCountryField.setRequired(true)).setVersioned(false);

//        form.add(homeStreetField.add(StringValidator.lengthBetween(Constants.MIN_LENGTH, Constants.MAX_LENGTH_STREET))).setVersioned(false);
//        form.add(homeCommuneField.add(StringValidator.lengthBetween(Constants.MIN_LENGTH, Constants.MAX_LENGTH_COMMUNE))).setVersioned(false);
//        form.add(homeCountryField.add(StringValidator.lengthBetween(Constants.MIN_LENGTH, Constants.MAX_LENGTH_COUNTRY))).setVersioned(false);
    }

    /**
     * Trigger the prcoessing method and parse the response
     * and
     * add the components that have to be refreshed within UI
     */
    public void handleTextFieldUpdate(AjaxRequestTarget target) {
        // invoke actual processing method
        GoogleMapResponse googleMapResponse = processGoogleRequest();
        Integer distance = CollectionUtilities.firstElement(googleMapResponse.getVoyages()) == null ? 0 : CollectionUtilities.firstElement(googleMapResponse.getVoyages()).getVoyageDistance();
        Integer duration = CollectionUtilities.firstElement(googleMapResponse.getVoyages()) == null ? 0 : CollectionUtilities.firstElement(googleMapResponse.getVoyages()).getVoyageDuration();

        LOG.debug("Retrieved from Google: distance (in metres) : " + distance);
        LOG.debug("Retrieved from Google: duration (in seconds) : " + duration);

        get("commutePageForm").get("distance").setDefaultModelObject(distance / 1000);
        get("commutePageForm").get("duration").setDefaultModelObject(duration / 60);

        target.add(get("commutePageForm").get("distance"));
        target.add(get("commutePageForm").get("duration"));
    }

    /**
     * Process a Google Request to retrieve distance and duration
     *
     * @return GoogleMapResponse response with distance and duration
     */
    private GoogleMapResponse processGoogleRequest() {
        GoogleMapRequest googleMapRequest = new GoogleMapRequest();

        googleMapRequest.setOfficeStreet(get("commutePageForm").get("officeStreet").getDefaultModelObjectAsString());
        googleMapRequest.setOfficeCommune(get("commutePageForm").get("officeCommune").getDefaultModelObjectAsString());
        googleMapRequest.setOfficeCountry(get("commutePageForm").get("officeCountry").getDefaultModelObjectAsString());

        googleMapRequest.setHomeStreet(get("commutePageForm").get("homeStreet").getDefaultModelObjectAsString());
        googleMapRequest.setHomeCommune(get("commutePageForm").get("homeCommune").getDefaultModelObjectAsString());
        googleMapRequest.setHomeCountry(get("commutePageForm").get("homeCountry").getDefaultModelObjectAsString());

        GoogleMapResponse googleMapResponse = null;
        try {
            googleMapResponse = googleMapFactoryService.getGoogleMapService().getGoogleDistance(googleMapRequest);
        } catch (Exception e) {
            LOG.error(">>>GoogleMapService is not available: exception = " + e);
            error("GoogleMapService is not available");
        }
        return googleMapResponse;
    }


}
