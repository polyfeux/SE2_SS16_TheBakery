package org.hbrs.se2.model.objects.dto;

import java.io.Serializable;

/**
 *
 * @author Felix
 */
public class Produkt implements Serializable {

    private int id;
    private String status;
    private double preis;
    private String name;
    private String beschreibung;
    private int kategorieId;
    private int menge;
    private int shopId;

    public Produkt() {
    }

    public Produkt(int id, int menge) {
        this.id = id;
        this.menge = menge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKategorieId() {
        return kategorieId;
    }

    public void setKategorieId(int kategorieId) {
        this.kategorieId = kategorieId;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

}
