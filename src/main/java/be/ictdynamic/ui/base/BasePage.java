package be.ictdynamic.ui.base;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public abstract class BasePage extends WebPage{

  public String feedbackMessage = "test";

  public BasePage() {
      initGui();
    }

  private void initGui() {
      add(new FeedbackPanel(feedbackMessage));
  }
}
