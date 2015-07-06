package be.ictdynamic.ui;

import be.ictdynamic.ui.base.BasePage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 * Created by 50038706 on 05/07/2015.
 */
public class AddCommentPage extends BasePage {
    private String author;
    private String comment;

    public AddCommentPage() {
        initGui();
    }

    private void initGui() {

        Form<AddCommentPage> addCommentForm = new Form<AddCommentPage>("addCommentForm", new CompoundPropertyModel<AddCommentPage>(this));
        add(addCommentForm);

        Label authorLabel = new Label("authorLabel", "Author:");
        addCommentForm.add(authorLabel);

        RequiredTextField<String> authorField = new RequiredTextField<String>("author");
        addCommentForm.add(authorField);

        Label commentLabel = new Label("commentLabel", "Comment:");
        addCommentForm.add(commentLabel);

        RequiredTextField<String> commentField = new RequiredTextField<String>("comment");
        addCommentForm.add(commentField);

        Button submitButton = new Button("submitButton") {
            @Override
            public void onSubmit() {
                getSession().info("Comment added successfully");
                setResponsePage(Dummy.class);
            }
        };
        addCommentForm.add(submitButton);
    }

}

