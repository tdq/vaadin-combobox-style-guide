package org.vaadin.nikolay;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 * <p>
 * The main view contains a text field for getting the user name and a button
 * that shows a greeting message in a notification.
 */
@Route
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@CssImport(value = "./styles/vaadin-combo-box-styles.css", themeFor = "vaadin-combo-box")
@CssImport(value = "./styles/vaadin-combo-box-overlay-styles.css", themeFor = "vaadin-combo-box-overlay")
@CssImport(value = "./styles/vaadin-combo-box-items-styles.css", themeFor = "vaadin-combo-box-item")
public class MainView extends VerticalLayout {

    private Mode currentMode = Mode.DARK;

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     */
    public MainView() {

        // Use TextField for standard text input
        final ComboBox<String> comboBox = new ComboBox<>("Select items");
        comboBox.setItems("Item 1", "Item 2", "Item 3");

        // Button click listeners can be defined as lambda expressions
        final Button button = new Button(currentMode.getModeName());
        button.addClickListener(e -> {
            currentMode = Mode.nextMode(currentMode);
            button.setText(currentMode.getModeName());
            UI.getCurrent().getElement().setAttribute("mode", currentMode.name());
        });

        // Theme variants give you predefined extra styles for components.
        // Example: Primary button is more prominent look.
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // Use custom CSS classes to apply styling. This is defined in
        // shared-styles.css.
        addClassName("centered-content");

        add(button, comboBox);
    }

    private enum Mode {
        LIGHT("Light theme"), DARK("Dark theme");

        private String modeName;

        Mode(String modeName) {
            this.modeName = modeName;
        }

        String getModeName() {
            return modeName;   
        }

        static Mode nextMode(Mode mode) {
            switch(mode) {
                case LIGHT: return Mode.DARK;
                case DARK: return Mode.LIGHT;
                default: return Mode.LIGHT;
            }
        }
    }
}
