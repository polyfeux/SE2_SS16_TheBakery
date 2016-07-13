package org.hbrs.se2.model.objects.dto;

/**
 *
 * @author Felix
 */
public class Adresse {

    private int id;
    private String stadt;
    private String ortsteil;
    private String plz;
    private String strasse;
    private String hausnr;
    private String bundesland;
    private String land;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getOrtsteil() {
        return ortsteil;
    }

    public void setOrtsteil(String ortsteil) {
        this.ortsteil = ortsteil;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnr() {
        return hausnr;
    }

    public void setHausnr(String hausnr) {
        this.hausnr = hausnr;
    }

    public String getBundesland() {
        return bundesland;
    }

    public void setBundesland(String bundesland) {
        this.bundesland = bundesland;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }
}
