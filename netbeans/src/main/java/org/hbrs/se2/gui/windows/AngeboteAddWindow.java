package org.hbrs.se2.gui.windows;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import java.util.List;
import org.hbrs.se2.gui.ui.MyUI;
import org.hbrs.se2.model.objects.dto.Kategorie;
import org.hbrs.se2.model.objects.dto.Produkt;
import org.hbrs.se2.model.objects.dto.User;
import org.hbrs.se2.process.control.KategorieControl;
import org.hbrs.se2.process.control.ShopControl;

/**
 *
 * @author Felix
 */
public class AngeboteAddWindow extends Window {

    /*
        notwendige Eingabe für ein Produkt:
        Produktname
        Produktbezeichnung
        Produktkategorie
        Preis
        Menge
     */
    public AngeboteAddWindow(String caption) {
        super(caption);
        this.setWidth(700.0f, Unit.PIXELS);
        this.setHeight(400.0f, Unit.PIXELS);
        this.center();
        setClosable(true);
        setModal(true);

        User user = ((MyUI) UI.getCurrent()).getUser();

        final FormLayout content = new FormLayout();
        content.setSizeFull();
        content.setSpacing(true);

        final TextField produktNameEingabe = new TextField("Produktname:");
        produktNameEingabe.setWidth(100.0f, Unit.PERCENTAGE);
        produktNameEingabe.setInputPrompt("Hier Produktname eintragen");
        produktNameEingabe.setRequired(true);
        content.addComponent(produktNameEingabe);

        final TextArea produktBeschreibungEingabe = new TextArea("Produktbeschreibung:");
        produktBeschreibungEingabe.setWidth(100.0f, Unit.PERCENTAGE);
        produktBeschreibungEingabe.setRows(5);
        produktBeschreibungEingabe.setValue("");

        content.addComponent(produktBeschreibungEingabe);

        final NativeSelect kategorieAuswahl = new NativeSelect("Wähle die Produktkategrie");
        List<Kategorie> liste = KategorieControl.getProduktKategorien();
        
        for (Kategorie k : liste) {
            kategorieAuswahl.addItem(k.getId());
            kategorieAuswahl.setItemCaption(k.getId(), k.getId() + " - " + k.getName());
        }
        
        kategorieAuswahl.setNullSelectionAllowed(false);
        kategorieAuswahl.setValue(liste.get(0).getId());
        kategorieAuswahl.setImmediate(true);
        kategorieAuswahl.setRequired(true);

        content.addComponent(kategorieAuswahl);

        final TextField produktPreis = new TextField("Preis (Format: 12.34):");
        produktPreis.setWidth(20.0f, Unit.PERCENTAGE);
        produktPreis.setValue("0.0");
        content.addComponent(produktPreis);

        final Button addEintragButton = new Button("Produkt hinzufügen");
        content.addComponent(addEintragButton);

        addEintragButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Produkt p = new Produkt();

                p.setName(produktNameEingabe.getValue());
                p.setBeschreibung(produktBeschreibungEingabe.getValue());
                p.setPreis(Double.valueOf(produktPreis.getValue()));
                p.setKategorieId((int) kategorieAuswahl.getValue());

                ShopControl.addProduktZuShop(p, user);

                produktNameEingabe.setValue("");
                produktBeschreibungEingabe.setValue("");
                kategorieAuswahl.setValue(1);
                produktPreis.setValue("0.0");
                
                close();
                
            }
        });

        this.setContent(content);
    }

}
