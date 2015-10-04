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
import org.springframework.context.ApplicationContext;

import java.util.Date;

public final class FirstPage extends BasePage {
    private ApplicationContext ctx;
    public Date date;
    public String comment;

//    private static final List<Comment> commentList = Collections.synchronizedList(new ArrayList<Comment>());
//    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @SpringBean
    private HelloWorldService helloWorldService;

    /**
     * Constructor that is invoked when page is invoked without a session.
     */
    public FirstPage() {
        initGui();
//        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    private void initGui() {

        Form<FirstPage> firstPageForm = new Form<FirstPage>("firstPageForm", new CompoundPropertyModel<FirstPage>(this));
        add(firstPageForm);

        TextField comment = new TextField("comment");
        if (helloWorldService == null) {
            System.out.println("helloWorldService.getMessage() = empty");
            comment.setModel(Model.of("Spring is not always fun"));
        }
        else {
            System.out.println("helloWorldService.getMessage() = " + helloWorldService.getMessage());
            comment.setModel(Model.of(helloWorldService.getMessage() + " ik begrijp het niet helemaal"));
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

//    public HelloWorldService getHelloWorldService() {
//        return helloWorldService;
//    }
//
//    public void setHelloWorldService(HelloWorldService helloWorldService) {
//        this.helloWorldService = helloWorldService;
//    }
//
//    public HelloWorldService getHelloWorldService() {
//        return (HelloWorldService) BeanFactoryUtils.beanOfType(ctx, HelloWorldService.class);
//    }

}
