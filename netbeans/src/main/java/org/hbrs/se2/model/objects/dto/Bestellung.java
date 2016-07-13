package org.hbrs.se2.model.objects.dto;

import com.vaadin.ui.UI;
import java.io.Serializable;
import java.util.ArrayList;
import org.hbrs.se2.services.util.Views;

/**
 *
 * @author C, Felix
 */
public class Bestellung implements Serializable {

    private int id;
    private String login;
    private ArrayList<BestellPosition> warenkorb = new ArrayList<>();
    private int GesamtPreis;
    private String datum;
    private String status = "#läuftBeiUns";

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public ArrayList<BestellPosition> getProdukteVonWarenkorb() {
        return warenkorb;
    }

    public void addBestellPosition(Produkt produkt, int menge) {
        if (produktVorhanden(produkt)) {
        } else {
            warenkorb.add(new BestellPosition(produkt, menge));
        }
    }

    public int getGesamtPreis() {
        return GesamtPreis;
    }

    public void setGesamtPreis(int GesamtPreis) {
        this.GesamtPreis = GesamtPreis;
    }

    public void warenkorbLoeschen() {
        warenkorb.clear();
        UI.getCurrent().getNavigator().navigateTo(Views.WARENKORB);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    private boolean produktVorhanden(Produkt produkt) {
        for (BestellPosition bP : warenkorb) {
            if (bP.getProdukt().getName().equals(produkt.getName())) {
                bP.setMenge(bP.getMenge() + 1);
                return true;
            }
        }
        return false;
    }
}
