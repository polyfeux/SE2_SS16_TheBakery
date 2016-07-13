package org.hbrs.se2.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Resource;
import com.vaadin.ui.Flash;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Video;
import org.hbrs.se2.gui.components.TopPanel;

/**
 *
 * @author C
 */
public class HilfeView extends VerticalLayout implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.setUp();
    }

    public void setUp() {
        this.addComponent(new TopPanel());
        this.addComponent(new Label("Hilfe-Seite"));

        // Banner
        String adPath = "img/BannerHelpVer.2.0.jpg";
        Image ad = new Image();
        ad.setSource(new ExternalResource(adPath));
        this.addComponent(ad);

        // Video aus Youtube einbinden
        // URL-Format: http://www.youtube.com/v/uX_tBxxlhYQ&hl=en_US&fs=1&
        Flash sample = new Flash(null, new ExternalResource(
                "http://www.youtube.com/v/uX_tBxxlhYQ&hl=de_DE&fs=1&rel=0"));
        sample.setParameter("allowFullScreen", "true");
        sample.setWidth(1024.0f, Unit.PIXELS);
        sample.setHeight(576.0f, Unit.PIXELS);

//        Video v = new Video();
//        final Resource r = new ExternalResource("http://www.youtube.com/v/uX_tBxxlhYQ&hl=de_DE&fs=1&rel=0");
//        v.setSource(r);
//        v.setSizeFull();
//        v.setHtmlContentAllowed(true);
//        v.setAltText("Can't play media");

        this.addComponent(sample);
//        this.addComponent(v);
    }
}
