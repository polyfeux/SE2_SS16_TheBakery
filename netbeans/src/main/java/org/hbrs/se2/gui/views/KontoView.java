package org.hbrs.se2.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.hbrs.se2.gui.components.TopPanel;
import org.hbrs.se2.gui.ui.MyUI;
import org.hbrs.se2.model.objects.dto.Shop;
import org.hbrs.se2.model.objects.dto.User;
import org.hbrs.se2.process.control.LoginControl;
import org.hbrs.se2.process.control.ShopControl;
import org.hbrs.se2.services.util.Roles;
import org.hbrs.se2.services.util.Views;

/**
 *
 * @author Felix
 */
public class KontoView extends VerticalLayout implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.setUp();
    }

    private void setUp() {
        this.addComponent(new TopPanel());
        this.addComponent(new Label("Mein Konto"));

        VerticalLayout vL = new VerticalLayout();
        this.addComponent(vL);
        HorizontalLayout hL1 = new HorizontalLayout();
        vL.addComponent(hL1);
        this.setComponentAlignment(vL, Alignment.TOP_CENTER);

        User user = ((MyUI) UI.getCurrent()).getUser();

        // Meine Bestellungen
        VerticalLayout vL11 = new VerticalLayout();
        vL11.setCaption("Meine Bestellungen");
        vL11.setWidth(33, Unit.PERCENTAGE);

        Button bestellungsubersicht = new Button("Übersicht aller Bestellungen", FontAwesome.LIST);
        vL11.addComponent(bestellungsubersicht);
        
        bestellungsubersicht.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                UI.getCurrent().getNavigator().navigateTo(Views.BESTELLHISTORIE);
            }
        });

        Button shopErstellenButton = new Button("Shop erstellen", FontAwesome.AMAZON);
        if (!user.hasRole(Roles.VERKAEUFER)) {
            vL11.addComponent(shopErstellenButton);
        }
        shopErstellenButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                ShopControl.shopErstellen(user);
                Shop shop = ShopControl.getShopVonUser(user);
                int shopId = shop.getId();
                Notification.show("Shop #" + shopId + " wurde erstellt", Notification.Type.ERROR_MESSAGE);
                LoginControl.logoutUser();
            }
        });

        hL1.addComponent(vL11);

        // Einstellungen
        VerticalLayout vL12 = new VerticalLayout();
        vL12.setCaption("Einstellungen");
        vL12.setWidth(33, Unit.PERCENTAGE);

        Button loginAendern = new Button("Namen, E-Mail-Adresse oder Passwort ändern", FontAwesome.SIGN_IN);
        vL12.addComponent(loginAendern);
        Button adressAendern = new Button("Adresse ändern", FontAwesome.TRUCK);
        vL12.addComponent(adressAendern);
        Button kontoLoeschen = new Button("Konto löschen", FontAwesome.BOMB);
        vL12.addComponent(kontoLoeschen);

        hL1.addComponent(vL12);

        // Benachrichtigungen
        VerticalLayout vL13 = new VerticalLayout();
        vL13.setCaption("Benachrichtigungen");
        vL13.setWidth(33, Unit.PERCENTAGE);

        NativeSelect benachrichtigungWaehlen = new NativeSelect();
        benachrichtigungWaehlen.addItem(0);
        benachrichtigungWaehlen.setItemCaption(0, "Benachrichtigungen aktivieren");
        benachrichtigungWaehlen.addItem(1);
        benachrichtigungWaehlen.setItemCaption(1, "Benachrichtigungen deaktivieren");
        benachrichtigungWaehlen.setNullSelectionAllowed(false);
        benachrichtigungWaehlen.setValue(0);
        benachrichtigungWaehlen.setImmediate(true);
        vL13.addComponent(benachrichtigungWaehlen);

        // evtl. über Window statt komplett eigener View lösen
        Button benachrichtungsFilter = new Button("Benachrichtigungsfilter", FontAwesome.CHECK_SQUARE_O);
        vL13.addComponent(benachrichtungsFilter);
        hL1.addComponent(vL13);

    }

}
