package org.hbrs.se2.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.hbrs.se2.gui.components.TopPanel;
import org.hbrs.se2.gui.ui.MyUI;
import org.hbrs.se2.model.dao.BestellungDAO;
import org.hbrs.se2.model.objects.dto.BestellPosition;
import org.hbrs.se2.model.objects.dto.Bestellung;
import org.hbrs.se2.services.util.Views;

/**
 *
 * @author C
 */
public class WarenkorbView extends VerticalLayout implements View {

    Bestellung bestellung = null;
    int anzahlProdukte = 0;
    double gesamtPreis;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.bestellung = ((MyUI) UI.getCurrent()).getWarenkorb();
        this.bestellung.setLogin(((MyUI) UI.getCurrent()).getUser().getLogin());
        this.anzahlProdukte = bestellung.getProdukteVonWarenkorb().size();
        this.setUp();
    }

    public void setUp() {
        this.addComponent(new TopPanel());
        this.addComponent(new Label("WarenkorbView"));

        if (this.bestellung.getProdukteVonWarenkorb().isEmpty() == true) {
            this.addComponent(new Label("Ihr Warenkorb ist leer!"));
        } else {
            Button bt_warenkorbLoeschen = new Button("Warenkorb löschen");
            Button bt_bestellen = new Button("Bestellung abschließen");

            // Grid erstellen
            Grid gridWarenkorb = new Grid();
            gridWarenkorb.setCaption("Ihr Warenkorb:");
            gridWarenkorb.setWidth(100, Unit.PERCENTAGE);
            gridWarenkorb.setHeightMode(HeightMode.ROW);
            gridWarenkorb.setEditorEnabled(false);
            gridWarenkorb.setSelectionMode(SelectionMode.NONE);

            // Kopfzeilen vom Grid festlegen
            gridWarenkorb.addColumn("index", String.class)
                    .setHeaderCaption("ID")
                    .setExpandRatio(0).setEditable(false);

            gridWarenkorb.addColumn("beschreibung", String.class)
                    .setHeaderCaption("Beschreibung")
                    .setExpandRatio(3).setEditable(false);

            gridWarenkorb.addColumn("einzelpreis", String.class)
                    .setHeaderCaption("Einzelpreis (€)")
                    .setExpandRatio(1).setEditable(false);

            gridWarenkorb.addColumn("menge", String.class)
                    .setHeaderCaption("Menge")
                    .setExpandRatio(1).setEditable(false);

            gridWarenkorb.addColumn("preis", String.class)
                    .setHeaderCaption("Preis (€)")
                    .setExpandRatio(1).setEditable(false);

            // Sortieren via Klick auf Spaltenkopf deaktivieren
            for (Grid.Column column : gridWarenkorb.getColumns()) {
                column.setSortable(false);
            }

            // Grid mit Inhalt fuellen
            for (BestellPosition bestellPosition : bestellung.getProdukteVonWarenkorb()) {
                String id = String.valueOf(bestellPosition.getProdukt().getId());
                String name = bestellPosition.getProdukt().getName();
                String einzelpreis = String.valueOf(bestellPosition.getProdukt().getPreis());
                String menge = String.valueOf(bestellPosition.getMenge());
                String preis = String.valueOf(Double.valueOf(menge) * bestellPosition.getProdukt().getPreis());
                
                gridWarenkorb.addRow(id, name, einzelpreis, menge, preis);
                gesamtPreis += Double.valueOf(preis);
            }

            gridWarenkorb.addRow("", "Gesamtpreis", "", "", String.valueOf(gesamtPreis));

            bt_warenkorbLoeschen.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    bestellung.warenkorbLoeschen();
                }
            });

            bt_bestellen.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    final Window windowBestellung = new Window("Bestellung kostenpflichtig bestellen");
                    final Window bestellungErfolgreich = new Window("Ihre Bestellung...");
                    windowBestellung.setWidth(600.0f, Unit.PIXELS);
                    windowBestellung.center();
                    windowBestellung.setModal(true);
                    windowBestellung.setResizable(false);
                    VerticalLayout vLayout = new VerticalLayout();
                    HorizontalLayout hLayout = new HorizontalLayout();
                    hLayout.setSpacing(true);

                    Button bt_ja = new Button("Ja");
                    Button bt_nein = new Button("Nein");

                    hLayout.addComponent(bt_ja);
                    hLayout.addComponent(bt_nein);

                    vLayout.addComponent(new Label("Jetzt kostenpflichtig bestellen?"));
                    vLayout.addComponent(hLayout);

                    bt_nein.addClickListener(new Button.ClickListener() {
                        @Override
                        public void buttonClick(Button.ClickEvent event) {
                            windowBestellung.setModal(false);
                            windowBestellung.close();
                        }
                    });

                    bt_ja.addClickListener(new Button.ClickListener() {
                        @Override
                        public void buttonClick(Button.ClickEvent event) {
                            BestellungDAO.getInstance().setBestellung(bestellung);

                            windowBestellung.setModal(false);
                            windowBestellung.close();
                            Button bt_Hauptmenue = new Button("Zurück zur Hauptseite");

                            bestellungErfolgreich.setWidth(600.0f, Unit.PIXELS);
                            bestellungErfolgreich.center();
                            bestellungErfolgreich.setModal(true);
                            bestellungErfolgreich.setResizable(false);

                            UI.getCurrent().addWindow(bestellungErfolgreich);

                            VerticalLayout vLayoutBestellungErfolgreich = new VerticalLayout();
                            vLayoutBestellungErfolgreich.addComponent(new Label("Ihre Bestellung war erfolgreich!"));
                            vLayoutBestellungErfolgreich.addComponent(bt_Hauptmenue);
                            bestellungErfolgreich.setContent(vLayoutBestellungErfolgreich);

                            bt_Hauptmenue.addClickListener(new Button.ClickListener() {
                                @Override
                                public void buttonClick(Button.ClickEvent event) {
                                    bestellung.warenkorbLoeschen();
                                    bestellungErfolgreich.setModal(false);
                                    bestellungErfolgreich.close();
                                    UI.getCurrent().getNavigator().navigateTo(Views.START);
                                }
                            });

                        }
                    });

                    windowBestellung.setContent(vLayout);

                    UI.getCurrent().addWindow(windowBestellung);
                }
            });

            this.addComponent(gridWarenkorb);

            HorizontalLayout hL = new HorizontalLayout();
            hL.addComponent(bt_warenkorbLoeschen);
            hL.addComponent(new Label("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;", ContentMode.HTML));
            hL.addComponent(bt_bestellen);
            this.addComponent(hL);
        }
    }

}
