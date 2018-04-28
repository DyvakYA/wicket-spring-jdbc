package controller;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.target.basic.RedirectRequestTarget;
import org.apache.wicket.spring.injection.annot.SpringBean;
import service.UserServiceImpl;

import java.io.Serializable;

public class LoginPage extends WebPage {
    private static final long serialVersionUID = 1L;

    @SpringBean
    private UserServiceImpl userService;

    public LoginPage(final PageParameters parameters) {

        super(parameters);
        add(new FeedbackPanel("feedback"));

        final TextField<Serializable> email = new TextField<>("login", new Model<>());
        email.setConvertEmptyInputStringToNull(false);
        email.setRequired(true);

        final PasswordTextField password = new PasswordTextField("password", new Model<>());
        password.setConvertEmptyInputStringToNull(false);
        password.setRequired(true);

        final Form<?> form = new Form<Void>("loginForm") {
            @Override
            protected void onSubmit() {
                String email1 = (String) email.getConvertedInput();
                if (userService.findUserByEmail(email1))
                    getRequestCycle().setRequestTarget(new RedirectRequestTarget("/find"));
                info("form go");
            }
        };

        form.add(email);
        form.add(password);

        add(form);
    }
}
