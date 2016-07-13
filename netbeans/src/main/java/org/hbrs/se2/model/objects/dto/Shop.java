package org.hbrs.se2.model.objects.dto;

import java.util.Date;

/**
 *
 * @author Felix
 */
public class Shop {

    private int id;
    private String shopname;
    private String login;
    private int shopkategorieid;
    private String shopbeschreibung;
    private Date eroeffnungsdatum;
    private int adressid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getShopkategorieid() {
        return shopkategorieid;
    }

    public void setShopkategorieid(int shopkategorieid) {
        this.shopkategorieid = shopkategorieid;
    }

    public String getShopbeschreibung() {
        return shopbeschreibung;
    }

    public void setShopbeschreibung(String shopbeschreibung) {
        this.shopbeschreibung = shopbeschreibung;
    }

    public Date getEroeffnungsdatum() {
        return (Date) eroeffnungsdatum.clone();
    }

    public void setEroeffnungsdatum(Date eroeffnungsdatum) {
        this.eroeffnungsdatum = eroeffnungsdatum;
    }

    public int getAdressid() {
        return adressid;
    }

    public void setAdressid(int adressid) {
        this.adressid = adressid;
    }
}
