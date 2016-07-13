package org.hbrs.se2.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.hbrs.se2.gui.components.AdPanel;
import org.hbrs.se2.gui.components.GridPanel;
import org.hbrs.se2.gui.components.TopPanel;
import org.hbrs.se2.model.dao.ProduktDAO;
import org.hbrs.se2.model.objects.dto.Produkt;
import org.hbrs.se2.process.control.exceptions.KeineProdukteException;
import org.hbrs.se2.services.util.Views;

/**
 *
 * @author Felix
 */
public class StartView extends VerticalLayout implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
//        event.getParameters()
//
//
        this.setUp();
    }

    public void setUp() {
        TopPanel t = new TopPanel();
        this.addComponent(t);
        
        AdPanel ad = new AdPanel();
        this.addComponent(ad);

        String suchBegriff = getSucheStringFromURL();
        Label gesuchtNachLabel;

        List<Produkt> suchErgebnisse;
        
        try {
            suchErgebnisse = ProduktDAO.getInstance().getProdukte(suchBegriff);
            if (suchBegriff.isEmpty()) {
                gesuchtNachLabel = new Label("Alle Artikel:");
            } else {
                gesuchtNachLabel = new Label(suchErgebnisse.size() + " Ergebnisse für \"" + suchBegriff + "\":");
            }
            this.addComponent(gesuchtNachLabel);
            this.addComponent(new GridPanel(suchErgebnisse));

        } catch (KeineProdukteException ex) {
            Logger.getLogger(StartView.class.getName()).log(Level.SEVERE, null, ex);
            Notification.show("Keine Produkte gefunden!", Notification.Type.ASSISTIVE_NOTIFICATION);
        }

        this.setStyleName("wrapper");
//        this.setSizeFull();
    }

    public String getSucheStringFromURL() {
        String s = UI.getCurrent().getPage().getLocation().toString();
        String davor = Pattern.quote("/#!");
        s = s.substring(21, s.length());
        String[] array1 = s.split(davor);
        String danach = Pattern.quote("/");
        String[] array2 = array1[1].split(danach);
        String suchBegriff = array2[array2.length - 1];
        if (suchBegriff.equals(Views.START)) {
            return "";
        }
        suchBegriff = suchBegriff.replace("%20", " ");
        return suchBegriff;
    }
}
