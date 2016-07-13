package org.hbrs.se2.model.objects.dto;

import java.io.Serializable;

/**
 *
 * @author Felix
 */
public class Role implements Serializable {

    private String bezeichnung = null;

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
}
