package org.hbrs.se2.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import org.hbrs.se2.gui.components.TopPanel;
import org.hbrs.se2.gui.ui.MyUI;
import org.hbrs.se2.model.dao.BestellungDAO;
import org.hbrs.se2.model.objects.dto.BestellPosition;
import org.hbrs.se2.model.objects.dto.Bestellung;
import org.hbrs.se2.model.objects.dto.Produkt;
import org.hbrs.se2.model.objects.dto.User;

/**
 *
 * @author Felix
 */
public class BestellungsHistorieView extends VerticalLayout implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setUp();
    }

    private void setUp() {
        this.addComponent(new TopPanel());
        this.addComponent(new Label("Meine Bestellhistorie:"));

        User user = ((MyUI) UI.getCurrent()).getUser();

        List<Bestellung> liste = BestellungDAO.getInstance().getBestellungen(user);

        if (liste.isEmpty()) {
            this.addComponent(new Label("Keine Bestellungen gefunden!"));
        } else {
            VerticalLayout vL;
            Grid tabelle;
            for (Bestellung b : liste) {
                vL = new VerticalLayout();

                List<BestellPosition> positionen = b.getProdukteVonWarenkorb();
                vL.addComponent(new Label("Am " + b.getDatum().toString().substring(0, 10) + " haben wir Deine Bestellung mit " + positionen.size() + " Positionen erhalten:"));

                tabelle = new Grid();
                tabelle.setWidth(100, Unit.PERCENTAGE);
                tabelle.setEditorEnabled(false);
                tabelle.setSelectionMode(SelectionMode.NONE);
                tabelle.addColumn("col1", String.class).setHeaderCaption("Artikelname");
                tabelle.addColumn("col2", String.class).setHeaderCaption("Menge");
                tabelle.addColumn("col3", String.class).setHeaderCaption("Einzelpreis");
                tabelle.addColumn("col4", String.class).setHeaderCaption("Preis (€)");

                // Sortieren via Klick auf Spaltenkopf deaktivieren
                for (Grid.Column column : tabelle.getColumns()) {
                    column.setSortable(false);
                }

                double gesamtSumme = 0;
                for (BestellPosition bP : positionen) {
                    String artikelname = bP.getProdukt().getName();
                    String menge = String.valueOf(bP.getMenge());
                    String einzelpreis = String.valueOf(bP.getProdukt().getPreis());
                    String preis = String.valueOf(Double.valueOf(menge) * bP.getProdukt().getPreis());

                    gesamtSumme = gesamtSumme + Double.valueOf(preis);

                    tabelle.addRow(artikelname, menge, einzelpreis, preis);
                }
                tabelle.addRow("Gesamtsumme", "", "", String.valueOf(gesamtSumme));
//
                vL.addComponent(tabelle);
                this.addComponent(vL);
            }

        }
    }
}
