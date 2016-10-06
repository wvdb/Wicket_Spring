package be.ictdynamic.ui;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.request.Response;

/**
 * Class RequiredFieldBehavior.
 *
 * @author Wim Van den Brande
 * @since 10/02/2016 - 9:22
 */
public class RequiredFieldBehavior extends Behavior {
    private static final long serialVersionUID = 2314193149416868821L;

    @Override
     public void beforeRender(Component component) {
         Response response = component.getResponse();
         StringBuffer asteriskHtml = new StringBuffer(200);

         if(component instanceof FormComponent
               && ((FormComponent)component).isRequired()){
           asteriskHtml.append(" <b style=\"color:red;font-size:small\">*</b>");
         }
         response.write(asteriskHtml);
     }
}
