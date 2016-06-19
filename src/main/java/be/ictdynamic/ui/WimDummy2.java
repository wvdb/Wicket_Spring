package be.ictdynamic.ui;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.TextField;

/**
 * Class WimRadioChoice.
 *
 * @author Wim Van den Brande
 * @since 17/02/2016 - 14:43
 */
public class WimDummy2<T> extends TextField {
    private static final long serialVersionUID = 7342106072286826791L;

    public WimDummy2(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        // all dom events: http://www.w3schools.com/jsref/dom_obj_event.asp
        this.add(new AjaxFormComponentUpdatingBehavior("onChange") {
            private static final long serialVersionUID = 4650984070993921421L;

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                ((CommutePage) getParent().getParent()).handleRadioButtonUpdate(target);
            }
        });
    }


}
