package org.hbrs.se2.model.objects.dto;

import java.io.Serializable;

/**
 *
 * @author Felix
 */
public class BestellPosition implements Serializable {

    private Produkt produkt;
    private int menge;

    public BestellPosition() {

    }

    public BestellPosition(Produkt produkt, int menge) {
        this.produkt = produkt;
        this.menge = menge;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

}
