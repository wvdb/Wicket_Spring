package be.ictdynamic.ui;

import be.ictdynamic.ui.base.BasePage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

public class ResponsePage extends BasePage {
    private String myResponse;
    private String author;
    private String comment;

//    public ResponsePage(final PageParameters parameters) {
//        if (parameters.containsKey("reponseGoogleMap")) {
//            setMyResponse(parameters.getString("reponseGoogleMap"));
//        }
//
//        initGui();
//    }

    // page should be non-bookmarkable
    public ResponsePage(String myResponse) {
        this.myResponse = myResponse;

        initGui();
    }

    private void initGui() {

        StatelessForm<ResponsePage> responseForm = new StatelessForm<ResponsePage>("responseForm", new CompoundPropertyModel<ResponsePage>(this));
        add(responseForm);

        Label responseLabel = new Label("responseLabel", "Response:");
        responseForm.add(responseLabel);

        RequiredTextField<String> responseField = new RequiredTextField<String>("response");
        responseField.setModel(Model.of(getMyResponse()));
        responseForm.add(responseField);

        Label authorLabel = new Label("authorLabel", "Author:");
        responseForm.add(authorLabel);

        RequiredTextField<String> authorField = new RequiredTextField<String>("author");
        responseForm.add(authorField);

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

    public String getMyResponse() {
        return myResponse;
    }

    public void setMyResponse(String myResponse) {
        this.myResponse = myResponse;
    }
}

