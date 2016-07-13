package org.hbrs.se2.gui.ui;

import com.vaadin.annotations.PreserveOnRefresh;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.hbrs.se2.gui.views.KontoView;
import org.hbrs.se2.gui.views.LoginView;
import org.hbrs.se2.gui.views.ShopBackendView;
import org.hbrs.se2.gui.views.AngeboteVerwaltenView;
import org.hbrs.se2.gui.views.BestellungsHistorieView;
import org.hbrs.se2.gui.views.HilfeView;
import org.hbrs.se2.gui.views.ShopStatistikView;
import org.hbrs.se2.gui.views.StartView;
import org.hbrs.se2.gui.views.WarenkorbView;
import org.hbrs.se2.model.objects.dto.User;
import org.hbrs.se2.model.objects.dto.Bestellung;
import org.hbrs.se2.services.util.Views;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("org.hbrs.se2.thebakery_shoppingv01.MyAppWidgetset")
@Title("Shopping@Shopping@HBRS")
@PreserveOnRefresh
public class MyUI extends UI {

    private User user = null;
    private Bestellung warenkorb = null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bestellung getWarenkorb() {
        return warenkorb;
    }

    public void setWarenkorb(Bestellung warenkorb) {
        this.warenkorb = warenkorb;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        System.out.println("LOG: neues UI-Objekt erzeugt. Session-ID: " + VaadinSession.getCurrent().toString());
        Navigator navi = new Navigator(this, this);

        navi.addView(Views.LOGIN, LoginView.class);
        navi.addView(Views.START, StartView.class);
        navi.addView(Views.WARENKORB, WarenkorbView.class);
        navi.addView(Views.KONTO, KontoView.class);
        navi.addView(Views.SHOPBACKEND, ShopBackendView.class);
        navi.addView(Views.ANGEBOTEVERWALTEN, AngeboteVerwaltenView.class);
        navi.addView(Views.SHOPSTATISTIKVIEW, ShopStatistikView.class);
        navi.addView(Views.BESTELLHISTORIE, BestellungsHistorieView.class);
        navi.addView(Views.HILFE, HilfeView.class);

        UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
    }

    public MyUI getMyUI() {
        return (MyUI) UI.getCurrent();
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
