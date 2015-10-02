package be.ictdynamic.ui;

import be.ictdynamic.ui.base.BasePage;
import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;

import java.util.Date;

public final class FirstPage extends BasePage {
    private Date date;
    private String text;

//    private static final List<Comment> commentList = Collections.synchronizedList(new ArrayList<Comment>());
//    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * Constructor that is invoked when page is invoked without a session.
     */
    public FirstPage() {
        initGui();
    }

    private void initGui() {

        Form<FirstPage> firstPageForm = new Form<FirstPage>("firstPageForm", new CompoundPropertyModel<FirstPage>(this));
        add(firstPageForm);

        firstPageForm.add(new TextField<String>("text").setType(String.class)).setVersioned(false);

        //Date field
        DateTextField dateTextField = new DateTextField("date",
                                                            new PropertyModel<Date>(this, "date"),
                                                            new PatternDateConverter("dd/MM/YYYY", true));

        DatePicker datePicker = new DatePicker();
        datePicker.setShowOnFieldClick(true);
        datePicker.setAutoHide(true);
        datePicker.setShowOnFieldClick(true);
        dateTextField.add(datePicker);

        dateTextField.setRequired(true);
        dateTextField.forDatePattern("date", "dd/MM/yyyy");
        firstPageForm.add(dateTextField);

        Button submitButton = new Button("submitButton") {
            @Override
            public void onSubmit() {
                setResponsePage(AddCommentPage.class);
            }
        };

        firstPageForm.add(submitButton);

//        add(new Link<WebPage>("addLink1") {
//            @Override
//            public void onClick() {
//                setResponsePage(new FirstPage());
//            }
//        });
    }

//    public static void clear() {
//        commentList.clear();
//    }
}
