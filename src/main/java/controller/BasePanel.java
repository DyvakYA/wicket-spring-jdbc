package controller;

import entity.User;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.target.basic.RedirectRequestTarget;
import org.apache.wicket.spring.injection.annot.SpringBean;
import service.UserServiceImpl;

import java.io.Serializable;

/**
 * Created by User on 4/26/2018.
 */
public class BasePanel extends Panel {

    @SpringBean
    private UserServiceImpl userService;

    public BasePanel(String id) {
        super(id, new Model());
        setOutputMarkupId(true);

        final Form<?> form = new Form("form");

        final TextField<Serializable> userId = new TextField<>("id", new Model<>());
        userId.setConvertEmptyInputStringToNull(false);

        final TextField<Serializable> firstname = new TextField<>("firstname", new Model<>());

        final TextField<Serializable> secondname = new TextField<>("secondname", new Model<>());

        final TextField<Serializable> age = new TextField<>("age", new Model<>());

        form.add(userId);
        form.add(firstname);
        form.add(secondname);
        form.add(age);

        form.add(new AjaxButton("back", form) {
                     @Override
                     protected void onSubmit(AjaxRequestTarget target, Form form) {
                         getRequestCycle().setRequestTarget(new RedirectRequestTarget("/find"));
                     }
                 }
        );

        form.add(new AjaxButton("submit", form) {
                     @Override
                     protected void onSubmit(AjaxRequestTarget target, Form form) {
                         User user = new User.Builder()
                                 .setFirstName(firstname.getValue())
                                 .setSecondName(secondname.getValue())
                                 .setAge(Integer.valueOf(age.getValue()))
                                 .build();
                         userService.createUser(user);
                         info("form go");
                         getRequestCycle().setRequestTarget(new RedirectRequestTarget("/user"));
                     }
                 }
        );

        form.add(new AjaxButton("button", form) {
                     @Override
                     protected void onSubmit(AjaxRequestTarget target, Form form) {
                         User user = userService.findUser(Integer.valueOf(userId.getValue()));
                         if (firstname != null)
                             user.setFirstName(firstname.getValue());
                         if (secondname != null)
                             user.setSecondName(secondname.getValue());
                         if (age != null)
                             user.setAge(Integer.valueOf(age.getValue()));
                         userService.updateUser(Integer.valueOf(userId.getValue()), user);
                         info("button1.onSubmit executed");
                         getRequestCycle().setRequestTarget(new RedirectRequestTarget("/user"));
                     }
                 }
        );
        add(form);
        form.setOutputMarkupId(true);
    }

}
