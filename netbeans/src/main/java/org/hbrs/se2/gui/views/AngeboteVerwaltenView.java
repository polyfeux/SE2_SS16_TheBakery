package org.hbrs.se2.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hbrs.se2.gui.components.GridPanel;
import org.hbrs.se2.gui.components.TopPanel;
import org.hbrs.se2.gui.ui.MyUI;
import org.hbrs.se2.gui.windows.AngeboteAddWindow;
import org.hbrs.se2.model.dao.ProduktDAO;
import org.hbrs.se2.model.objects.dto.Produkt;
import org.hbrs.se2.model.objects.dto.User;
import org.hbrs.se2.process.control.ShopControl;
import org.hbrs.se2.process.control.exceptions.KeineProdukteException;
import org.hbrs.se2.services.util.Views;

/**
 *
 * @author Felix
 */
public class AngeboteVerwaltenView extends VerticalLayout implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setUp();
    }

    public void setUp() {
        TopPanel t = new TopPanel();
        this.addComponent(t);

        User user = ((MyUI) UI.getCurrent()).getUser();

        HorizontalLayout hL = new HorizontalLayout();

        this.addComponent(new Label(ShopControl.getShopVonUser(user).getShopname() + ": Angebote verwalten"));

        Button viewAktualisieren = new Button("aktualisieren", FontAwesome.ROTATE_RIGHT);
        hL.addComponent(viewAktualisieren);

        viewAktualisieren.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                UI.getCurrent().getNavigator().navigateTo(Views.ANGEBOTEVERWALTEN);
            }
        });

        hL.addComponent(new Label("&nbsp;&nbsp;", ContentMode.HTML));

        Button addAngebot = new Button("neues Produkt hinzufügen", FontAwesome.PLUS);
        hL.addComponent(addAngebot);

        final AngeboteAddWindow addAngebotWindow = new AngeboteAddWindow("Produkt hinzufügen");

        addAngebot.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                UI.getCurrent().addWindow(addAngebotWindow);
            }
        });

        this.addComponent(hL);

        Label deineProdukteLabel = new Label("Deine Produkte:");
        this.addComponent(deineProdukteLabel);
        // evtl. noch ein "Ansicht aktualisieren"-Button

        List<Produkt> eigeneProdukte;

        try {
            eigeneProdukte = ProduktDAO.getInstance().getProdukteFromShop("", user);
            this.addComponent(new GridPanel(eigeneProdukte));

        } catch (KeineProdukteException ex) {
            Logger.getLogger(AngeboteVerwaltenView.class.getName()).log(Level.SEVERE, null, ex);
            Notification.show("Keine Produkte gefunden!", Notification.Type.ASSISTIVE_NOTIFICATION);
        }

    }

}
