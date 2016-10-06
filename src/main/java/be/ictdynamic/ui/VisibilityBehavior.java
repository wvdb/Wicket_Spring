package be.ictdynamic.ui;

import org.apache.commons.collections4.Predicate;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;

/**
 * Class VisibilityBehavior.
 *
 * @author Wim Van den Brande
 * @since 19/01/2016 - 12:26
 */
public class VisibilityBehavior extends Behavior {
    private static final long serialVersionUID = 8764377255935204567L;
    private Predicate<Component> predicate;

    public VisibilityBehavior(Predicate<Component> logic) {
        this.setPredicate(logic);
    }

    public void onConfigure(Component component) {
        super.onConfigure(component);
        component.setVisibilityAllowed(this.getPredicate().evaluate(component));
    }

    protected Predicate<Component> getPredicate() {
        return this.predicate;
    }

    private void setPredicate(Predicate<Component> predicate) {
        this.predicate = predicate;
    }

}
