package org.hbrs.se2.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.hbrs.se2.gui.ui.MyUI;
import org.hbrs.se2.model.objects.dto.User;
import org.hbrs.se2.process.control.LoginControl;
import org.hbrs.se2.process.control.exceptions.DatabaseException;
import org.hbrs.se2.process.control.exceptions.NoSuchUserOrPassword;
import org.hbrs.se2.services.util.Views;

/**
 *
 * @author Felix
 */
public class LoginView extends VerticalLayout implements View {

    public void setUp() {
        this.setSizeFull();

        final Label label = new Label("Bitte loggen Sie sich ein:");

        final TextField userLogin = new TextField();
        userLogin.setCaption("User:");
        userLogin.focus();

        final PasswordField passwordField = new PasswordField();
        passwordField.setCaption("Passwort:");

        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(label);
        layout.addComponent(userLogin);
        layout.addComponent(passwordField);

        Panel panel = new Panel("Shopping@Shopping@HBRS");
        panel.addStyleName("login");

        this.addComponent(panel);
        this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

        panel.setContent(layout);

        Button buttonLogin = new Button("Login", FontAwesome.CARET_RIGHT);
        layout.addComponent(buttonLogin);
        layout.setComponentAlignment(buttonLogin, Alignment.MIDDLE_CENTER);

        panel.setSizeUndefined();

        buttonLogin.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                String login = userLogin.getValue();
                String password = passwordField.getValue();

                try {
                    LoginControl.checkAuthentication(login, password);
                } catch (NoSuchUserOrPassword ex) {
                    Notification.show("Fehler", "User oder Passwort falsch", Notification.Type.ERROR_MESSAGE);
                    userLogin.setValue("");
                    passwordField.setValue("");
                } catch (DatabaseException ex) {
                    Notification.show("DB-Fehler", ex.getReason(), Notification.Type.ERROR_MESSAGE);
                    userLogin.setValue("");
                    passwordField.setValue("");
                }
            }
        });
        // Enter-Taste betaetigt auch den Login-Button
        buttonLogin.setClickShortcut(com.vaadin.event.ShortcutAction.KeyCode.ENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
        User user = ((MyUI) UI.getCurrent()).getUser();

        if (user != null) {
            UI.getCurrent().getNavigator().navigateTo(Views.START);
        } else {
            this.setUp();
        }
    }

}
