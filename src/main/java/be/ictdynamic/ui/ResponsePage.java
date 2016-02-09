package be.ictdynamic.ui;

import be.ictdynamic.domain.GoogleMapResponse;
import be.ictdynamic.ui.base.BasePage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

public class ResponsePage extends BasePage {
    private static final long serialVersionUID = 5869901981141048197L;
    private Double lat = 0.0;
    private Double lng = 0.0;

    // required because of Wicket's CompoundPropertyModel
    public String comment;

    // page should be non-bookmarkable
    public ResponsePage(GoogleMapResponse googleMapResponse) {
        if (googleMapResponse != null) {
            this.lat = googleMapResponse.getLat();
            this.lng = googleMapResponse.getLng();
        }
        initGui();
    }

    private void initGui() {

        StatelessForm<ResponsePage> responseForm = new StatelessForm<>("responseForm", new CompoundPropertyModel<>(this));
        add(responseForm);

        Label responseLabel = new Label("latLabel", "Latitude: (*)");
        responseForm.add(responseLabel);

        RequiredTextField<String> latField = new RequiredTextField<>("lat");
        latField.setModel(Model.of(getLat().toString()));
        responseForm.add(latField);

        Label authorLabel = new Label("lngLabel", "Longitude: (*)");
        responseForm.add(authorLabel);

        RequiredTextField<String> lngField = new RequiredTextField<>("lng");
        lngField.setModel(Model.of(getLng().toString()));
        responseForm.add(lngField);

        Label commentLabel = new Label("commentLabel", "Comment: (*)");
        responseForm.add(commentLabel);

        TextField<String> commentField = new TextField<>("comment");
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

        Button backButton = new Button("backButton") {
            private static final long serialVersionUID = 5824523915446192700L;

            @Override
            public void onSubmit() {
                setResponsePage(CommutePage.class);
            }
        };
        responseForm.add(backButton);
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

}

