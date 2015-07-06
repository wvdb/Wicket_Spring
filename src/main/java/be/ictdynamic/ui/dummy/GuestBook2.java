package be.ictdynamic.ui.dummy;

import be.ictdynamic.data.Comment;
import org.apache.commons.lang.StringUtils;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.value.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public final class GuestBook2 extends WebPage {
    /** A global list of all comments from all users across all sessions */
    private static final List<Comment> commentList = Collections.synchronizedList(new ArrayList<Comment>());
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * Constructor that is invoked when page is invoked without a session.
     */
    public GuestBook2() {
        LOGGER.info("starting Dummy() now");
        // Add comment form
        add(new CommentForm("commentForm"));

        // Add commentListView of existing comments
        add(new PropertyListView<Comment>("comments", commentList) {
            @Override
            public void populateItem(final ListItem<Comment> listItem) {
                listItem.add(new Label("date"));
                listItem.add(new Label("author"));
                listItem.add(new MultiLineLabel("text"));
            }
        }).setVersioned(false);

        Button submitButton = new Button("formsubmit") {          // (4)
            @Override
            public void onSubmit() {
                System.out.println("OnSubmit, name = ");
            }
        };

        add(submitButton);
    }

    /**
     * A form that allows a user to add a comment.
     */
    public final class CommentForm extends Form<ValueMap> {
        public CommentForm(final String id) {
            // Construct form with no validation listener
            super(id, new CompoundPropertyModel<ValueMap>(new ValueMap()));
            LOGGER.info("starting CommentForm()");

            // this is just to make the unit test happy
            setMarkupId("commentForm");

            add(new TextField<String>("text").setType(String.class));
            add(new TextField<String>("author").setType(String.class));
        }

        @Override
        public final void onSubmit() {
            LOGGER.info("starting onSubmit()");
            getSession().info("Adding Comment");

            ValueMap values = getModelObject();

            if (StringUtils.isNotBlank((String)values.get("text"))) {
                error("Caught a spammer!!!");
                return;
            }

            // Construct a copy of the edited comment
            Comment comment = new Comment();

            comment.setDate(new Date());
            comment.setText((String) values.get("text"));
            comment.setAuthor((String) values.get("author"));

            // add using index 0
            commentList.add(0, comment);

            values.put("text", "");
            values.put("author", "");
            getSession().info("Comment added successfully");
        }
    }

    public static void clear() {
        commentList.clear();
    }
}
