package controller;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;


public class WicketApplication  extends WebApplication {
    @Override
    public Class<? extends Page> getHomePage() {
        return LoginPage.class;
    }

    @Override
    protected void init() {
        super.init();

        getMarkupSettings().setStripWicketTags(true);
        addComponentInstantiationListener(new SpringComponentInjector(this));

        mountBookmarkablePage("/home", LoginPage.class);
        mountBookmarkablePage("/user", UserPage.class);
        mountBookmarkablePage("/find", FindPage.class);
    }



}
