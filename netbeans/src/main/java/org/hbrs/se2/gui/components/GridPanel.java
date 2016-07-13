package org.hbrs.se2.gui.components;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import org.hbrs.se2.model.objects.dto.Produkt;

/**
 *
 * @author C
 * @author Felix
 */
public class GridPanel extends VerticalLayout {

    private int zeilenAnzahlBerechnen(int produktAnzahl, int spaltenAnzahl) {
        int i = 1;
        int zeilenAnz = 0;
        do {
            zeilenAnz = spaltenAnzahl * i;
            i++;
        } while (produktAnzahl >= zeilenAnz);

        return (zeilenAnz / spaltenAnzahl);
    }

    public GridPanel(List<Produkt> liste) {

        int produktAnzahl = liste.size();
        int spaltenAnzahl = 3;
        int zeilenAnzahl = zeilenAnzahlBerechnen(produktAnzahl, spaltenAnzahl);

        GridLayout grid = new GridLayout(spaltenAnzahl, zeilenAnzahl);
        grid.setWidth("80%");

        int counter = 0;

        for (Produkt p : liste) {
            grid.addComponent(new ProduktItem(p));
        }

        this.addComponent(grid);
//        this.setComponentAlignment(grid, Alignment.TOP_CENTER);

    }
}
