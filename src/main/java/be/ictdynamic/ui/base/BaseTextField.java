package be.ictdynamic.ui.base;

import be.ictdynamic.ui.CommutePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.TextField;

/**
 * Class BaseTextField.
 *
 * @author Wim Van den Brande
 * @since 06/10/2016 - 15:40
 */
public class BaseTextField extends TextField{
    private static final long serialVersionUID = -2406597866697697411L;

    public BaseTextField(String id) {
        super(id);
        this.setOutputMarkupId(true);
        this.setOutputMarkupPlaceholderTag(true);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        // all dom events: http://www.w3schools.com/jsref/dom_obj_event.asp
        this.add(new AjaxFormComponentUpdatingBehavior("onChange") {

            private static final long serialVersionUID = -3738689518095087643L;

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                ((CommutePage) getParent().getParent()).handleTextFieldUpdate(target);
            }
        });
    }

}
