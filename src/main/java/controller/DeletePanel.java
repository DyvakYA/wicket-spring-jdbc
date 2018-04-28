package controller;

import entity.User;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.target.basic.RedirectRequestTarget;
import org.apache.wicket.spring.injection.annot.SpringBean;
import service.UserServiceImpl;

import java.io.Serializable;
import java.util.List;

/**
 * Created by User on 4/26/2018.
 */
public class DeletePanel extends Panel {

    @SpringBean
    private UserServiceImpl userService;

    public DeletePanel(String id) {
        super(id, new Model());

        final TextField<Serializable> userId = new TextField<>("id", new Model<>());
        userId.setConvertEmptyInputStringToNull(false);

        final Form<?> form = new Form<Void>("delete_form");

        List<User> list = userService.findAll();

        form.add(new ListView<User>("listview", list) {
            @Override
            protected void populateItem(final ListItem<User> item) {
                final User user = item.getModelObject();
                item.add(new Label("userId", String.valueOf(user.getId())));
                item.add(new Label("firstname", user.getFirstName()));
                item.add(new Label("secondname", user.getSecondName()));
                item.add(new Label("age", String.valueOf(user.getAge())));
            }
        });

        add(form);
        form.add(userId);

        AjaxButton button = new AjaxButton("delete", form) {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form form) {
                userService.deleteUser(Integer.parseInt(userId.getValue()));
                getRequestCycle().setRequestTarget(new RedirectRequestTarget("/user"));
            }
        };
        form.add(button);
    }
}





