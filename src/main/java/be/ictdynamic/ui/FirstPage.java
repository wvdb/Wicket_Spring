package be.ictdynamic.ui;

import be.ictdynamic.services.HelloWorldService;
import be.ictdynamic.ui.base.BasePage;
import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public final class FirstPage extends BasePage {
    public Date date;
    public String comment;

    private static final Logger LOG = LoggerFactory.getLogger(FirstPage.class);

    @SpringBean
    private HelloWorldService helloWorldService;

    /**
     * Constructor that is invoked when page is invoked without a session.
     */
    public FirstPage() {
        initGui();
    }

    private void initGui() {

        Form<FirstPage> firstPageForm = new Form<FirstPage>("firstPageForm", new CompoundPropertyModel<FirstPage>(this));
        add(firstPageForm);

        TextField comment = new TextField("comment");
        if (helloWorldService == null) {
            LOG.warn(">>>This is not supposed to happen");
            comment.setModel(Model.of("Spring is not always fun"));
        } else {
            LOG.debug(">>>Spring config ok .... ready to roll");
            comment.setModel(Model.of(helloWorldService.getCountry()));
        }
        firstPageForm.add(comment).setVersioned(false);

        //Date field
        DateTextField dateTextField = new DateTextField("date",
                new PropertyModel<Date>(this, "date"),
                new PatternDateConverter("dd/MM/YYYY", true));

//        DatePicker datePicker = new DatePicker();
//        datePicker.setShowOnFieldClick(true);
//        datePicker.setAutoHide(true);
//        datePicker.setShowOnFieldClick(true);
//        dateTextField.add(datePicker);

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

    }

}
