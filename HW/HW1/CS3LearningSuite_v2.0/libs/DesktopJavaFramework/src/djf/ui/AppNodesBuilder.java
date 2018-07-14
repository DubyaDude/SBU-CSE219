package djf.ui;

import static djf.AppTemplate.PATH_ICONS;
import djf.modules.AppGUIModule;
import djf.ui.controllers.AppKeyController;
import djf.modules.AppLanguageModule;
import static djf.modules.AppLanguageModule.FILE_PROTOCOL;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import properties_manager.PropertiesManager;

/**
 * AppNodesBuilder
 *
 * This factory class provides convenient means for building common UI
 * components that can be easily integrated into DJF application.
 */
public class AppNodesBuilder {

    private AppLanguageModule languageSettings;
    private AppGUIModule gui;

    public AppNodesBuilder(AppGUIModule initGUI, AppLanguageModule initLanguageSettings) {
        gui = initGUI;
        languageSettings = initLanguageSettings;
    }

    public CheckBox buildCheckBox(Object nodeId,
            Pane parentPane,
            ToolBar parentToolBar,
            String styleClass,
            boolean usesKeyHandler,
            boolean focusTraversable,
            boolean enabled) {
        CheckBox checkBox = new CheckBox();
        initNode(nodeId.toString(), checkBox, parentPane, parentToolBar, styleClass, usesKeyHandler, focusTraversable, enabled);

        // MAKE SURE THE LANGUAGE MANAGER HAS IT
        // SO THAT IT CAN CHANGE THE LANGUAGE AS NEEDED
        initLabeledNode(nodeId, checkBox);

        // AND RETURN IT
        return checkBox;
    }

    public ColorPicker buildColorPicker(Object nodeId,
            Pane parentPane,
            ToolBar parentToolBar,
            String styleClass,
            boolean usesKeyHandler,
            boolean focusTraversable,
            boolean enabled) {
        ColorPicker colorPicker = new ColorPicker();
        initNode(nodeId, colorPicker, parentPane, parentToolBar, styleClass, usesKeyHandler, focusTraversable, enabled);
        return colorPicker;
    }

    public ComboBox buildComboBox(Object nodeId,
            Object optionsListProperty,
            Object defaultValueProperty,
            Pane parentPane,
            ToolBar parentToolBar,
            String styleClass,
            boolean usesKeyHandler,
            boolean focusTraversable,
            boolean enabled) {
        // MAKE AND INIT THE COMBO BOX
        ComboBox comboBox = new ComboBox();
        initNode(nodeId, comboBox, parentPane, parentToolBar, styleClass, usesKeyHandler, focusTraversable, enabled);

        // LOAD THE OPTIONS INTO THE COMBO BOX
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        ObservableList<String> items = comboBox.getItems();
        ArrayList<String> propertyOptions = props.getPropertyOptionsList(optionsListProperty);
        String defaultValue = props.getProperty(defaultValueProperty);
        if (defaultValue != null) {
            items.add(defaultValue);
            comboBox.getSelectionModel().select(defaultValue);
        }
        if (propertyOptions != null) {
            for (String s : propertyOptions) {
                if (!items.contains(s)) {
                    items.add(s);
                }
            }
        }

        return comboBox;
    }

    public HBox buildHBox(Object nodeId,
            Pane parentPane,
            ToolBar parentToolBar,
            String styleClass,
            boolean usesKeyHandler,
            boolean focusTraversable,
            boolean enabled) {
        HBox box = new HBox();
        initNode(nodeId, box, parentPane, parentToolBar, styleClass, usesKeyHandler, focusTraversable, enabled);
        return box;
    }

    public Label buildLabel(Object nodeId,
            Pane parentPane,
            ToolBar parentToolBar,
            String styleClass,
            boolean usesKeyHandler,
            boolean focusTraversable,
            boolean enabled) {
        // MAKE AN INIT THE LABEL
        Label labelToBuild = new Label();
        initNode(nodeId, labelToBuild, parentPane, parentToolBar, styleClass, usesKeyHandler, focusTraversable, enabled);

        // MAKE SURE THE LANGUAGE MANAGER HAS IT
        // SO THAT IT CAN CHANGE THE LANGUAGE AS NEEDED
        if (nodeId != null) {
        // MAKE SURE THE LANGUAGE MANAGER HAS IT
        // SO THAT IT CAN CHANGE THE LANGUAGE AS NEEDED
        initLabeledNode(nodeId, labelToBuild);
        }

        // AND RETURN IT. REMEMBER THAT THE TEXT AND TOOLTIP
        // WILL BE ADDED WHEN THE LANGUAGE SETTINGS ARE INITIALIZED
        // USING A LOADED LANGUAGE
        return labelToBuild;
    }
    private void initLabeledNode(Object nodeId, Labeled labeledNode) {
        languageSettings.addLabeledControlProperty(nodeId.toString() + "_TEXT", labeledNode.textProperty());
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        if (props.getProperty(nodeId.toString() + "_TOOLTIP") != null) {
            labeledNode.setTooltip(new Tooltip(""));
            languageSettings.addLabeledControlProperty(nodeId.toString() + "_TOOLTIP", labeledNode.tooltipProperty().get().textProperty());
        }
    }
    public Slider buildSlider(Object nodeId,
            Pane parentPane,
            ToolBar parentToolBar,
            String styleClass,
            int minValue,
            int maxValue,
            boolean usesKeyHandler,
            boolean focusTraversable,
            boolean enabled) {
        Slider slider = new Slider();
        initNode(nodeId, slider, parentPane, parentToolBar, styleClass, usesKeyHandler, focusTraversable, enabled);
        slider.setMin(minValue);
        slider.setMax(maxValue);
        slider.setSnapToTicks(true);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setBlockIncrement(1);
        slider.setShowTickLabels(false);
        slider.setShowTickMarks(true);
        slider.valueProperty().addListener((obs, oldval, newVal)
                -> slider.setValue(Math.round(newVal.doubleValue())));
        return slider;
    }

    public VBox buildVBox(Object nodeId,
            Pane parentPane,
            ToolBar parentToolBar,
            String styleClass,
            boolean usesKeyHandler,
            boolean focusTraversable,
            boolean enabled) {
        VBox box = new VBox();
        initNode(nodeId, box, parentPane, parentToolBar, styleClass, usesKeyHandler, focusTraversable, enabled);
        return box;
    }

    public Button buildIconButton(Object nodeId,
            Pane parentPane,
            ToolBar parentToolbar,
            String styleClass,
            boolean usesKeyHandler,
            boolean focusTraversable,
            boolean enabled) {
        // FIRST MAKE THE BASIC BUTTON
        Button button = buildTextButton(nodeId, parentPane, parentToolbar, styleClass, usesKeyHandler, focusTraversable, enabled);

        attachIcon(button, nodeId);

        // AND RETURN THE BUTTON
        return button;
    }

    public Button buildTextButton(Object nodeId,
            Pane parentPane,
            ToolBar parentToolBar,
            String styleClass,
            boolean usesKeyHandler,
            boolean focusTraversable,
            boolean enabled) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();

        // NOW MAKE THE BUTTON
        Button button = new Button();
        button.setWrapText(true);
        button.setTextAlignment(TextAlignment.CENTER);

        // INITIALIZE THE OTHER SETTINGS
        initNode(nodeId, button, parentPane, parentToolBar, styleClass, usesKeyHandler, focusTraversable, enabled);

        // MAKE SURE THE LANGUAGE MANAGER HAS IT
        // SO THAT IT CAN CHANGE THE LANGUAGE AS NEEDED
        initLabeledNode(nodeId, button);

        // AND RETURN THE COMPLETED BUTTON
        return button;
    }

    private void attachIcon(ButtonBase button, Object nodeId) {
        // LOAD THE ICON FROM THE PROVIDED FILE
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String iconProperty = nodeId.toString() + "_ICON";
        String imagePath = FILE_PROTOCOL + PATH_ICONS + props.getProperty(iconProperty);
        Image buttonImage = new Image(imagePath);
        if (!buttonImage.isError()) {
            button.setGraphic(new ImageView(buttonImage));
        }
    }

    public ToggleButton buildIconToggleButton(Object nodeId,
            Pane parentPane,
            ToolBar parentToolBar,
            String styleClass,
            boolean usesKeyHandler,
            boolean focusTraversable,
            boolean enabled) {
        // NOW MAKE THE TOGGLE BUTTON
        ToggleButton button = buildTextToggleButton(nodeId, parentPane, parentToolBar, styleClass, usesKeyHandler, focusTraversable, enabled);

        // ADD THE IMAGE
        attachIcon(button, nodeId);

        // AND RETURN THE COMPLETED BUTTON
        return button;
    }

    public ToggleButton buildTextToggleButton(Object nodeId,
            Pane parentPane,
            ToolBar parentToolBar,
            String styleClass,
            boolean usesKeyHandler,
            boolean focusTraversable,
            boolean enabled) {
        // NOW MAKE THE TOGGLE BUTTON
        ToggleButton button = new ToggleButton();

        // INITIALIZE THE OTHER SETTINGS
        initNode(nodeId, button, parentPane, parentToolBar, styleClass, usesKeyHandler, focusTraversable, enabled);

        // MAKE SURE THE LANGUAGE MANAGER HAS IT
        // SO THAT IT CAN CHANGE THE LANGUAGE AS NEEDED
        initLabeledNode(nodeId, button);

        button.setSelected(false);
        button.setDisable(false);

        // AND RETURN THE COMPLETED BUTTON
        return button;
    }

    // HELPER METHOD FOR INITIALIZING DJF NODES
    private void initNode(Object nodeId,
            Node node,
            Pane parentPane,
            ToolBar parentToolBar,
            String nodeStyleClass,
            boolean usesKeyHandler,
            boolean focusTraversable,
            boolean enabled) {
        // KEEP IT FOR LATER
        gui.addGUINode(nodeId, node);

        // PUT THE BUTTON IN THE PARENT PANE IF THERE IS ONE
        if (parentPane != null) {
            parentPane.getChildren().add(node);
        }

        // PUT THE BUTTON IN THE TOOLBAR IF THERE IS ONE
        if (parentToolBar != null) {
            parentToolBar.getItems().add(node);
        }

        // SET THE STYLE
        node.getStyleClass().add(nodeStyleClass);

        // NODE CAN RESPOND TO KEY PRESSES
        node.setFocusTraversable(focusTraversable);
        if (usesKeyHandler) {
            AppKeyController keyController = gui.getKeyController();
            node.addEventHandler(KeyEvent.ANY, keyController);
        }

        // ENABLE/DISABLE
        node.setDisable(!enabled);
    }

    public TextField buildTextField(Object nodeId,
            Pane parentPane,
            ToolBar parentToolBar,
            String styleClass,
            boolean usesKeyHandler,
            boolean focusTraversable,
            boolean enabled) {
        // NOW MAKE THE TEXT FIELD
        TextField textField = new TextField();

        // INITIALIZE THE OTHER SETTINGS
        initNode(nodeId, textField, parentPane, parentToolBar, styleClass, usesKeyHandler, focusTraversable, enabled);

        // AND RETURN THE COMPLETED BUTTON
        return textField;
    }

    public TableView buildTableView(Object nodeId,
            Pane parentPane,
            ToolBar parentToolBar,
            String styleClass,
            boolean usesKeyHandler,
            boolean focusTraversable,
            boolean enabled) {
        // NOW MAKE THE TABLE VIEW
        TableView tableView = new TableView();

        // INITIALIZE THE OTHER SETTINGS
        initNode(nodeId, tableView, parentPane, parentToolBar, styleClass, usesKeyHandler, focusTraversable, enabled);

        // AND RETURN THE COMPLETED BUTTON
        return tableView;
    }

    public TableColumn buildTableColumn(Object nodeId,
            TableView tableView,
            String styleClass) {
        TableColumn column = new TableColumn();
        tableView.getColumns().add(column);
        column.getStyleClass().add(styleClass);
        languageSettings.addLabeledControlProperty(nodeId.toString() + "_TEXT", column.textProperty());
        return column;
    }
}
