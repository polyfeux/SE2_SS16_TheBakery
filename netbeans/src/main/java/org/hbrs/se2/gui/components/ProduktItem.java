package org.hbrs.se2.gui.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.hbrs.se2.gui.ui.MyUI;
import org.hbrs.se2.gui.windows.AngebotDetailsWindow;
import org.hbrs.se2.model.objects.dto.Produkt;
import org.hbrs.se2.model.objects.dto.Bestellung;
import org.hbrs.se2.model.objects.dto.User;
import org.hbrs.se2.process.control.ShopControl;
import org.hbrs.se2.services.util.Roles;
import org.hbrs.se2.services.util.Views;

/**
 *
 * @author Felix
 */
public class ProduktItem extends VerticalLayout {

    public ProduktItem(Produkt p) {
        Label produktItem = new Label(p.getName());
        produktItem.setCaption("#" + String.valueOf(p.getId()) + " / " + String.valueOf(p.getPreis()) + " Euro");

        this.addComponent(produktItem);

        Button info = new Button("", FontAwesome.INFO);
        this.addComponent(info);

        final AngebotDetailsWindow angebotDetailsWindow = new AngebotDetailsWindow(p);

        info.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                UI.getCurrent().addWindow(angebotDetailsWindow);
            }
        });

        HorizontalLayout hL = new HorizontalLayout();

        Button bt_kaufMich = new Button("kauf mich");
        bt_kaufMich.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Bestellung warenkorb = ((MyUI) UI.getCurrent()).getWarenkorb();
                warenkorb.addBestellPosition(p, 1);
                Notification.show("Warenkorb", "Das Produkt " + p.getName() + " wurde in Ihren Warenkorb gelegt", Notification.Type.TRAY_NOTIFICATION);
            }
        });

        // loeschMich-Button koennte hier hin passen

        this.addComponent(produktItem);

        hL.addComponent(bt_kaufMich);
        this.addComponent(hL);

    }

}
