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
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
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

    @SpringBean
    private GoogleMapFactoryServiceImpl googleMapFactoryService;

    @SpringBean
    private SecurityService securityService;

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

        // OFFICE FIELDS

        final TextField<String> officeStreetField = new TextField<String>("officeStreet") {
            private static final long serialVersionUID = 8079540829151688207L;

            @Override
            protected void onInitialize() {
                super.onInitialize();

                // all dom events: http://www.w3schools.com/jsref/dom_obj_event.asp
                this.add(new AjaxFormComponentUpdatingBehavior("onChange") {
                    private static final long serialVersionUID = 4650984070993921421L;

                    @Override
                    protected void onUpdate(AjaxRequestTarget target) {
                        ((CommutePage) getParent().getParent()).handleTextFieldUpdate(target);
                    }
                });
            }
        };

        final TextField<String> officeCommuneField = new TextField<String >("officeCommune")  {

            private static final long serialVersionUID = -7011567939901389719L;

            @Override
                    protected void onInitialize() {
                        super.onInitialize();

                        // all dom events: http://www.w3schools.com/jsref/dom_obj_event.asp
                        this.add(new AjaxFormComponentUpdatingBehavior("onChange") {

                            private static final long serialVersionUID = -5768972302609935505L;

                            @Override
                            protected void onUpdate(AjaxRequestTarget target) {
                                ((CommutePage) getParent().getParent()).handleTextFieldUpdate(target);
                            }
                        });
                    }
                } ;

        final TextField<String> officeCountryField = new TextField<String>("officeCountry")  {

            private static final long serialVersionUID = 2427966590472602399L;

            @Override
                    protected void onInitialize() {
                        super.onInitialize();

                        // all dom events: http://www.w3schools.com/jsref/dom_obj_event.asp
                        this.add(new AjaxFormComponentUpdatingBehavior("onChange") {

                            private static final long serialVersionUID = -3738689518095087643L;

                            @Override
                            protected void onUpdate(AjaxRequestTarget target) {
                                ((CommutePage) getParent().getParent()).handleTextFieldUpdate(target);
                            }
                        });
                    }
                };

        // HOME FIELDS
        // -----------

        TextField<String> homeStreetField = new TextField<String>("homeStreet") {

            private static final long serialVersionUID = 2427966590472602399L;

            @Override
            protected void onInitialize() {
                super.onInitialize();

                // all dom events: http://www.w3schools.com/jsref/dom_obj_event.asp
                this.add(new AjaxFormComponentUpdatingBehavior("onChange") {

                    private static final long serialVersionUID = -3738689518095087643L;

                    @Override
                    protected void onUpdate(AjaxRequestTarget target) {
                        ((CommutePage) getParent().getParent()).handleTextFieldUpdate(target);
                    }
                });
            }
        };
        homeStreetField.setOutputMarkupId(true);
        homeStreetField.setOutputMarkupPlaceholderTag(true);
        TextField<String> homeCommuneField = new TextField<String>("homeCommune") {

            private static final long serialVersionUID = 2427966590472602399L;

            @Override
            protected void onInitialize() {
                super.onInitialize();

                // all dom events: http://www.w3schools.com/jsref/dom_obj_event.asp
                this.add(new AjaxFormComponentUpdatingBehavior("onChange") {

                    private static final long serialVersionUID = -3738689518095087643L;

                    @Override
                    protected void onUpdate(AjaxRequestTarget target) {
                        ((CommutePage) getParent().getParent()).handleTextFieldUpdate(target);
                    }
                });
            }
        };
        final TextField<String> homeCountryField = new TextField<String>("homeCountry") {

            private static final long serialVersionUID = 2427966590472602399L;

            @Override
            protected void onInitialize() {
                super.onInitialize();

                // all dom events: http://www.w3schools.com/jsref/dom_obj_event.asp
                this.add(new AjaxFormComponentUpdatingBehavior("onChange") {

                    private static final long serialVersionUID = -3738689518095087643L;

                    @Override
                    protected void onUpdate(AjaxRequestTarget target) {
                        ((CommutePage) getParent().getParent()).handleTextFieldUpdate(target);
                    }
                });
            }
        };

        // example of validation

        form.add(new Label("distance").setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true));
        form.add(new Label("duration").setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true));

        form.add(officeStreetField.setRequired(true)).setVersioned(false);
        form.add(officeCommuneField.setRequired(true)).setVersioned(false);
        form.add(officeCountryField.setRequired(true)).setVersioned(false);

        form.add(homeStreetField.add(StringValidator.lengthBetween(Constants.MIN_LENGTH, Constants.MAX_LENGTH_STREET))).setVersioned(false);
        form.add(homeCommuneField.add(StringValidator.lengthBetween(Constants.MIN_LENGTH, Constants.MAX_LENGTH_COMMUNE))).setVersioned(false);
        form.add(homeCountryField.add(StringValidator.lengthBetween(Constants.MIN_LENGTH, Constants.MAX_LENGTH_COUNTRY))).setVersioned(false);

    }

    /**
     * Add the components that have to be refreshed
     *
     */
    public void handleTextFieldUpdate(AjaxRequestTarget target) {
        GoogleMapResponse googleMapResponse = processGoogleRequest();

        Integer distance;
        Integer duration;
        try {
            distance = googleMapResponse.getVoyages().iterator().next().getVoyageDistance();
            duration = googleMapResponse.getVoyages().iterator().next().getVoyageDuration();
        }
        catch (NullPointerException e) {
            distance = 0;
            duration = 0;
        }

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
