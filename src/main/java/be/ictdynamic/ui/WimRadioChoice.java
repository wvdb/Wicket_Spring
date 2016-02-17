package be.ictdynamic.ui;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.model.PropertyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Class WimRadioChoice.
 *
 * @author Wim Van den Brande
 * @since 17/02/2016 - 14:43
 */
public class WimRadioChoice<T> extends RadioChoice {
    private static final Logger LOG = LoggerFactory.getLogger(WimRadioChoice.class);
    private static final long serialVersionUID = 7342106072286826791L;

    public WimRadioChoice(String id) {
        super(id);
    }

    public WimRadioChoice(String hostingTypes, PropertyModel<String> selected, List<String> hostTypes) {
        super(hostingTypes, selected, hostTypes);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        this.add(new AjaxFormChoiceComponentUpdatingBehavior() {
            private static final long serialVersionUID = 4650984070993921421L;

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                String selectedValue = getComponent().getDefaultModelObjectAsString();
                LOG.debug(">>>WimRadioChoice is being updated. Selected value:" + selectedValue);
                if (selectedValue.equals("Dedicated Server")) {
                    ((CommutePage) getParent().getParent()).handleRadioButtonUpdate(target);
                }
            }
        });
    }
}
