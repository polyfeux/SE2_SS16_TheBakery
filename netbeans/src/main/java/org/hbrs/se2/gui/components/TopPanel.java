package org.hbrs.se2.gui.components;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.hbrs.se2.gui.ui.MyUI;
import org.hbrs.se2.model.objects.dto.User;
import org.hbrs.se2.process.control.LoginControl;
import org.hbrs.se2.services.util.Roles;
import org.hbrs.se2.services.util.Views;

/**
 *
 * @author Felix
 */
public class TopPanel extends VerticalLayout {

    private String suchBegriff = "";

    public TopPanel() {
//        this.setSizeFull();
        VerticalLayout vL = new VerticalLayout();

        HorizontalLayout vLOben = new HorizontalLayout();
        vLOben.setHeight(5, Unit.PIXELS);
        HorizontalLayout vLUnten = new HorizontalLayout();
        vLUnten.setHeight(5, Unit.PIXELS);
        vL.addComponent(vLOben);
        vL.addComponent(vLUnten);

        String logoPath = "img/TheBakery_v2_nurLogo_small.png";
        Image logo = new Image();
        logo.setSource(new ExternalResource(logoPath));

        final Label uberschrift = new Label("Willkommen bei Shopping@HBRS &mdash; <i>frisch von TheBakery f&uuml;r Dich!</i>", ContentMode.HTML);
        uberschrift.setStyleName("uberschrift");

        Button homeButton = new Button("", FontAwesome.HOME);
        homeButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                UI.getCurrent().getNavigator().navigateTo(Views.START);
            }
        });

        int browserFensterBreite = UI.getCurrent().getPage().getBrowserWindowWidth();

        final TextField suchFeld = new TextField();
        suchFeld.addStyleName("suchFeld");
        suchFeld.setHeight((float) 2.4, Unit.EM);

        if (browserFensterBreite > 1360) {
            suchFeld.setWidth(500, Unit.PIXELS);
        } else {
            suchFeld.setWidth(browserFensterBreite - 860, Unit.PIXELS);
        }

        Button sucheButton = new Button("", FontAwesome.SEARCH);
        sucheButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                suchBegriff = suchFeld.getValue();
//                UI.getCurrent().getNavigator().navigateTo(getCurrentViewFromURI() + "/" + suchBegriff);
                UI.getCurrent().getNavigator().navigateTo(Views.START + "/" + suchBegriff);
            }
        });
        sucheButton.setClickShortcut(com.vaadin.event.ShortcutAction.KeyCode.ENTER);

        Button warenkorb = new Button("Warenkorb", FontAwesome.SHOPPING_CART);
        warenkorb.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                UI.getCurrent().getNavigator().navigateTo(Views.WARENKORB);
            }
        });

        User user = ((MyUI) UI.getCurrent()).getUser();

        String vorname = null;
        if (user != null) {
            vorname = user.getVorname();
        }

        /* ================== MenuBar ================== */
        MenuBar bar = new MenuBar();
        MenuBar.MenuItem item1 = bar.addItem("Hallo " + vorname + "!", FontAwesome.SMILE_O, null);

        item1.addItem(
                "Mein Konto", FontAwesome.FOLDER_OPEN_O, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UI.getCurrent().getNavigator().navigateTo(Views.KONTO);
            }
        }
        );

        // VERKAEUFER abfragen
        if (user.hasRole(Roles.VERKAEUFER)) {
            item1.addItem("Mein Shop", FontAwesome.DASHBOARD, new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem selectedItem) {
                    UI.getCurrent().getNavigator().navigateTo(Views.SHOPBACKEND);
                }
            });
        }

        // Logout des Users
        item1.addItem(
                "Logout", FontAwesome.SIGN_OUT, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                LoginControl.logoutUser();
            }
        }
        );

        Button bt_hilfe = new Button("Hilfe", FontAwesome.LIFE_SAVER);
        bt_hilfe.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                UI.getCurrent().getNavigator().navigateTo(Views.HILFE);
            }
        });
 
        vLOben.addComponent(logo);
        vLOben.setComponentAlignment(logo, Alignment.TOP_LEFT);

        vLOben.addComponent(new Label("&nbsp;&nbsp;&nbsp;", ContentMode.HTML));
        vLOben.addComponent(uberschrift);

        vLUnten.addComponent(homeButton);
        vLUnten.addComponent(new Label("&nbsp;&nbsp;&nbsp;", ContentMode.HTML));
        vLUnten.addComponent(suchFeld);
        vLUnten.addComponent(new Label("&nbsp;&nbsp;&nbsp;", ContentMode.HTML));
        vLUnten.addComponent(sucheButton);
        vLUnten.addComponent(new Label("&nbsp;&nbsp;&nbsp;", ContentMode.HTML));
        vLUnten.addComponent(warenkorb);
        vLUnten.addComponent(new Label("&nbsp;&nbsp;&nbsp;", ContentMode.HTML));
        vLUnten.addComponent(bar);
        vLUnten.addComponent(new Label("&nbsp;&nbsp;&nbsp;", ContentMode.HTML));
        vLUnten.addComponent(bt_hilfe);
        
        this.addComponent(vL);
//        this.setComponentAlignment(vL, Alignment.MIDDLE_CENTER);

    }

    public String sucheWeiterleiten() {
        return this.suchBegriff;
    }

//    public String getCurrentViewFromURI() {
//        String s = UI.getCurrent().getPage().getLocation().toString();
//        String p = Pattern.quote("/#!");
//        s = s.substring(21, s.length());
//        String[] sArray = s.split(p);
//        return sArray[1];
//    }
}
