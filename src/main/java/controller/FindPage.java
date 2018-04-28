package controller;

import entity.User;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.target.basic.RedirectRequestTarget;
import org.apache.wicket.spring.injection.annot.SpringBean;
import service.UserServiceImpl;

import java.io.Serializable;

/**
 * Created by User on 4/25/2018.
 */
public class FindPage extends WebPage {

    @SpringBean
    private UserServiceImpl userService;

    public FindPage(final PageParameters parameters) {

        super(parameters);

        add(new FeedbackPanel("feedback"));

        final TextField<Serializable> text = new TextField<Serializable>("query", new Model<>());
        text.setConvertEmptyInputStringToNull(false);

        final Form<?> form = new Form<Void>("find_form");

        WebMarkupContainer container = new WebMarkupContainer("container");
        container.setOutputMarkupId(true);
        form.add(container);

        ListView<User> listView = new ListView<User>("listview") {
            @Override
            protected void populateItem(ListItem<User> item) {
                item.add(new Label("label", item.getModel()));
            }
        };
        listView.setOutputMarkupId(true);
        container.add(listView);

        AjaxButton button = new AjaxButton("find", form) {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form form) {
                listView.setList(userService.findUserByName(text.getValue()));
                if (target != null)
                    target.addComponent(container);
            }
        };
        button.setDefaultFormProcessing(false);
        form.add(button);

        form.add(new AjaxButton("back", form) {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form form) {
                getRequestCycle().setRequestTarget(new RedirectRequestTarget("/home"));
            }
        });

        form.add(new AjaxButton("submit", form) {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form form) {
                getRequestCycle().setRequestTarget(new RedirectRequestTarget("/user"));
            }
        });

        form.add(text);
        add(form);

    }
}
