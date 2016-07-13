package org.hbrs.se2.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.hbrs.se2.gui.components.TopPanel;
import org.hbrs.se2.gui.ui.MyUI;
import org.hbrs.se2.model.objects.dto.User;
import org.hbrs.se2.process.control.ShopControl;
import org.hbrs.se2.services.util.Views;

/**
 *
 * @author Felix
 */
public class ShopBackendView extends VerticalLayout implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.setUp();
    }

    public void setUp() {
        this.addComponent(new TopPanel());

        User user = ((MyUI) UI.getCurrent()).getUser();
        this.addComponent(new Label(ShopControl.getShopVonUser(user).getShopname() + ": Shop-Backend"));

        VerticalLayout vL = new VerticalLayout();
        this.addComponent(vL);
        HorizontalLayout hL = new HorizontalLayout();
        vL.addComponent(hL);
        this.setComponentAlignment(vL, Alignment.TOP_CENTER);

        float containerBreite = 300;

        VerticalLayout vL3 = new VerticalLayout();
        vL3.setCaption("Meine Angebote");
        vL3.setWidth(containerBreite, Unit.PIXELS);
        hL.addComponent(vL3);

        Button angeboteVerwalten = new Button("Angebote verwalten", FontAwesome.INDENT);
        vL3.addComponent(angeboteVerwalten);

        angeboteVerwalten.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                UI.getCurrent().getNavigator().navigateTo(Views.ANGEBOTEVERWALTEN);
            }
        });

        Button statistikAufrufen = new Button("Statistik einsehen", FontAwesome.BAR_CHART);
        vL3.addComponent(statistikAufrufen);

        statistikAufrufen.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                UI.getCurrent().getNavigator().navigateTo(Views.SHOPSTATISTIKVIEW);
            }
        });

    }

}
