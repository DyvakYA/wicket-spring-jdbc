package controller;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

/**
 * Created by UserPage on 4/22/2018.
 */
public class UserPage extends WebPage {

    public UserPage(final PageParameters parameters) {

        super(parameters);
        add(new FeedbackPanel("feedback"));
        add(new BasePanel("base_panel"));
        add(new DeletePanel("delete_panel"));


    }
}
