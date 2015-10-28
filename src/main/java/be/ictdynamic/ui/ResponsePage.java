package be.ictdynamic.ui;

import be.ictdynamic.domain.GoogleMapResponse;
import be.ictdynamic.ui.base.BasePage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

public class ResponsePage extends BasePage {
    private Double lat = 0.0;
    private Double lng = 0.0;
    private String comment;

//    public ResponsePage(final PageParameters parameters) {
//        if (parameters.containsKey("reponseGoogleMap")) {
//            setMyResponse(parameters.getString("reponseGoogleMap"));
//        }
//
//        initGui();
//    }

    // page should be non-bookmarkable
    public ResponsePage(GoogleMapResponse googleMapResponse) {
        if (googleMapResponse != null) {
            this.lat = googleMapResponse.getLat();
            this.lng = googleMapResponse.getLng();
        }
        initGui();
    }

    private void initGui() {

        StatelessForm<ResponsePage> responseForm = new StatelessForm<ResponsePage>("responseForm", new CompoundPropertyModel<ResponsePage>(this));
        add(responseForm);

        Label responseLabel = new Label("latLabel", "Latitude:");
        responseForm.add(responseLabel);

        RequiredTextField<String> latField = new RequiredTextField<String>("lat");
        latField.setModel(Model.of(getLat().toString()));
        responseForm.add(latField);

        Label authorLabel = new Label("lngLabel", "Longitude:");
        responseForm.add(authorLabel);

        RequiredTextField<String> lngField = new RequiredTextField<String>("lng");
        lngField.setModel(Model.of(getLng().toString()));
        responseForm.add(lngField);

        Label commentLabel = new Label("commentLabel", "Comment:");
        responseForm.add(commentLabel);

        RequiredTextField<String> commentField = new RequiredTextField<String>("comment");
        responseForm.add(commentField);

        Button submitButton = new Button("submitButton") {
            private static final long serialVersionUID = 5824523915446192700L;

            @Override
            public void onSubmit() {
                getSession().info("Comment added successfully");
                setResponsePage(CommutePage.class);
            }
        };
        responseForm.add(submitButton);
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

}

