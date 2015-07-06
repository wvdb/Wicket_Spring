package be.ictdynamic.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import be.ictdynamic.data.Comment;
import be.ictdynamic.ui.base.BasePage;
import org.apache.commons.lang.StringUtils;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.value.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Dummy extends BasePage {

//    private static final List<Comment> commentList = Collections.synchronizedList(new ArrayList<Comment>());
//    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * Constructor that is invoked when page is invoked without a session.
     */
    public Dummy() {
        initGui();
    }

    private void initGui() {
        add(new TextField<String>("text").setType(String.class));
        add(new TextField<String>("author").setType(String.class));
        add(new Link<WebPage>("addLink1") {
            @Override
            public void onClick() {
                setResponsePage(new AddCommentPage());
            }
        });
    }

//    public static void clear() {
//        commentList.clear();
//    }
}
