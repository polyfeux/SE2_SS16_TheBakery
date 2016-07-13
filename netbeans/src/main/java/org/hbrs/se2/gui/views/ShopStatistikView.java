package org.hbrs.se2.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.Slider;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import org.hbrs.se2.gui.components.TopPanel;
import org.hbrs.se2.model.dao.StatistikDAO;
import org.hbrs.se2.model.objects.dto.StatistikEintrag;

/**
 *
 * @author Felix, T, P
 */
public class ShopStatistikView extends VerticalLayout implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.setUp();
    }

    private void setUp() {
        this.addComponent(new TopPanel());

        this.addComponent(new Label("StatistikView"));

        ArrayList<StatistikEintrag> altersStruktur = StatistikDAO.getInstance().getAltersStruktur();

        this.addComponent(new Label("Altersverteilung der Shop-User auf " + altersStruktur.size() + " Bereiche:"));

        int summe = 0;
        for (StatistikEintrag statistikEintrag : altersStruktur) {
            summe = summe + statistikEintrag.getAnzahl();
        }

        HorizontalLayout hL = null;
        Label label1;
        Label label2;
        ProgressBar s;
        
        for (StatistikEintrag statistikEintrag : altersStruktur) {
            hL = new HorizontalLayout();
            label1 = new Label(statistikEintrag.getName() + ": ");
            label2 = new Label(statistikEintrag.getAnzahl() + " Personen");

            s = new ProgressBar();
            s.setValue(statistikEintrag.getAnzahl() / Float.valueOf(summe));
            s.setWidth(300, Sizeable.Unit.PIXELS);

            hL.addComponent(label1);
            hL.addComponent(new Label("&nbsp;&nbsp;&nbsp;", ContentMode.HTML));
            hL.addComponent(s);
            hL.addComponent(new Label("&nbsp;&nbsp;&nbsp;", ContentMode.HTML));
            hL.addComponent(label2);

            this.addComponent(hL);
        }

        this.addComponent(new Label("\u03A3 = " + summe));

//        Slider s;
//        for (StatistikEintrag statistikEintrag : altersStruktur) {
//            hL = new HorizontalLayout();
//            label1 = new Label(statistikEintrag.getName() + ": ");
//            label2 = new Label(statistikEintrag.getAnzahl() + " Personen");
//            
//            s = new Slider();
//            s.setMax(1000);
//            s.setWidth(300, Sizeable.Unit.PIXELS);
//            s.setValue(statistikEintrag.getAnzahl() * 1.0);
//            s.setReadOnly(true);
//            
//            hL.addComponent(label1);
//            hL.addComponent(new Label("&nbsp;&nbsp;&nbsp;", ContentMode.HTML));
//            hL.addComponent(s);
//            hL.addComponent(new Label("&nbsp;&nbsp;&nbsp;", ContentMode.HTML));
//            hL.addComponent(label2);
//            
//            this.addComponent(hL);
//        }
    }

}
