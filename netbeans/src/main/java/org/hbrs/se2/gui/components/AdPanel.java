package org.hbrs.se2.gui.components;

import com.vaadin.event.MouseEvents;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author C
 */
public class AdPanel extends VerticalLayout {

    public AdPanel() {
        VerticalLayout vL = new VerticalLayout();
        int browserFensterBreite = UI.getCurrent().getPage().getBrowserWindowWidth();
        vL.setWidth(Integer.toString(browserFensterBreite));
        
        String adPath = "img/BannerVer.2.0.png";
        Image ad = new Image();
        ad.setSource(new ExternalResource(adPath));
        
        ad.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent event) {
                Notification sample = new Notification("Notification message",
                        "Notification description");
                sample.show("Der Gutscheincode wurde dem Warenkorb hinzugefügt!");
            }
        });
        vL.addComponent(ad);
        this.addComponent(vL);
    }

}
