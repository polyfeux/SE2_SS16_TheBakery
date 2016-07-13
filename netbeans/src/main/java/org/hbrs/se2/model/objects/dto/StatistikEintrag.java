package org.hbrs.se2.model.objects.dto;

/**
 *
 * @author Felix
 */
public class StatistikEintrag {

    private String name;
    private int anzahl;

    public StatistikEintrag(String name, int anzahl) {
        this.name = name;
        this.anzahl = anzahl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

}
