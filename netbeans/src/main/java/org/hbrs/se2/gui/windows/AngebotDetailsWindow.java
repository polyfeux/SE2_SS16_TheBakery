package org.hbrs.se2.gui.windows;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.hbrs.se2.model.objects.dto.Produkt;

/**
 *
 * @author Felix
 */
public class AngebotDetailsWindow extends Window {

    public AngebotDetailsWindow(Produkt p) {
        super("Details zu: " + p.getName());

        this.setWidth(500.0f, Unit.PIXELS);
        this.setHeight(300.0f, Unit.PIXELS);
        this.center();
        setClosable(true);
        setModal(true);

//        User user = ((MyUI) UI.getCurrent()).getUser();

        VerticalLayout vL = new VerticalLayout();

        vL.addComponent(new Label("Beschreibung: " + p.getBeschreibung()));
        vL.addComponent(new Label("Verkäufer-ID: " + p.getShopId()));
        vL.addComponent(new Label("Preis: " + p.getPreis() + " Euro"));

        this.setContent(vL);
    }
}
