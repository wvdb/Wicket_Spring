package be.ictdynamic.ui;

import be.ictdynamic.ui.base.BasePage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.model.CompoundPropertyModel;

public class ResponsePage extends BasePage {
    private String author;
    private String comment;

    public ResponsePage() {
        initGui();
    }

    private void initGui() {

//        Form<ResponsePage> addCommentForm = new Form<ResponsePage>("addCommentForm", new CompoundPropertyModel<ResponsePage>(this));
        StatelessForm<ResponsePage> responseForm = new StatelessForm<ResponsePage>("responseForm", new CompoundPropertyModel<ResponsePage>(this));
        add(responseForm);

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

}

