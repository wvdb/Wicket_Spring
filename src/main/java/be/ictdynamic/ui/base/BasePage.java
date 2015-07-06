package be.ictdynamic.ui.base;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

/**
 * Created by 50038706 on 06/07/2015.
 */
public abstract class BasePage extends WebPage{

  public BasePage() {
      initGui();
    }

  private void initGui() {
      add(new FeedbackPanel("feedbackPanel"));
  }
}
