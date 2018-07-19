package tdlm.workspace;

import djf.components.AppWorkspaceComponent;
import djf.modules.AppFoolproofModule;
import djf.modules.AppGUIModule;
import static djf.modules.AppGUIModule.DISABLED;
import static djf.modules.AppGUIModule.ENABLED;
import static djf.modules.AppGUIModule.FOCUS_TRAVERSABLE;
import static djf.modules.AppGUIModule.HAS_KEY_HANDLER;
import static djf.modules.AppGUIModule.NO_KEY_HANDLER;
import djf.ui.AppNodesBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;
import tdlm.ToDoListMakerApp;
import static tdlm.ToDoPropertyType.TDLM_FOOLPROOF_SETTINGS;
import static tdlm.workspace.style.TDLStyle.CLASS_TDLM_BIG_HEADER;
import static tdlm.workspace.style.TDLStyle.CLASS_TDLM_BUTTON;
import static tdlm.workspace.style.TDLStyle.CLASS_TDLM_COLUMN;
import static tdlm.workspace.style.TDLStyle.CLASS_TDLM_PROMPT;
import static tdlm.workspace.style.TDLStyle.CLASS_TDLM_TABLE;
import static tdlm.workspace.style.TDLStyle.CLASS_TDLM_TEXT_FIELD;
import static tdlm.ToDoPropertyType.TDLM_PANE;
import static tdlm.ToDoPropertyType.TDLM_TO_DO_LIST_LABEL;
import static tdlm.ToDoPropertyType.TDLM_OWNER_PANE;
import static tdlm.ToDoPropertyType.TDLM_OWNER_LABEL;
import static tdlm.ToDoPropertyType.TDLM_OWNER_TEXT_FIELD;
import static tdlm.ToDoPropertyType.TDLM_NAME_PANE;
import static tdlm.ToDoPropertyType.TDLM_NAME_LABEL;
import static tdlm.ToDoPropertyType.TDLM_NAME_TEXT_FIELD;
import static tdlm.ToDoPropertyType.TDLM_ITEMS_PANE;
import static tdlm.ToDoPropertyType.TDLM_ITEM_BUTTONS_PANE;
import static tdlm.ToDoPropertyType.TDLM_ADD_ITEM_BUTTON;
import static tdlm.ToDoPropertyType.TDLM_EDIT_ITEM_BUTTON;
import static tdlm.ToDoPropertyType.TDLM_MOVE_UP_ITEM_BUTTON;
import static tdlm.ToDoPropertyType.TDLM_MOVE_DOWN_ITEM_BUTTON;
import static tdlm.ToDoPropertyType.TDLM_CATEGORY_COLUMN;
import static tdlm.ToDoPropertyType.TDLM_COMPLETED_COLUMN;
import static tdlm.ToDoPropertyType.TDLM_DESCRIPTION_COLUMN;
import static tdlm.ToDoPropertyType.TDLM_ASSIGNED_TO_COLUMN;
import static tdlm.ToDoPropertyType.TDLM_REMOVE_ITEM_BUTTON;
import static tdlm.ToDoPropertyType.TDLM_ITEMS_TABLE_VIEW;
import static tdlm.ToDoPropertyType.TDLM_NAME_OWNER_PANE;
import static tdlm.ToDoPropertyType.TDLM_NAME_NAME_PANE;
import static tdlm.ToDoPropertyType.TDLM_START_DATE_COLUMN;
import static tdlm.ToDoPropertyType.TDLM_END_DATE_COLUMN;
import tdlm.workspace.controllers.ItemsController;
import tdlm.workspace.controllers.ItemsTableController;
import static tdlm.workspace.style.TDLStyle.CLASS_TDLM_BOX;
import tdlm.data.ToDoData;
import tdlm.data.ToDoItemPrototype;
import tdlm.workspace.foolproof.ToDoSelectionFoolproofDesign;
import tdlm.transactions.SortItems_Transaction;
import djf.modules.AppFileModule;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

/**
 *
 * @author McKillaGorilla
 */
public class ToDoWorkspace extends AppWorkspaceComponent {

    public ToDoWorkspace(ToDoListMakerApp app) {
        super(app);

        // LAYOUT THE APP
        initLayout();
        
        // 
        initFoolproofDesign();
    }
        
    // THIS HELPER METHOD INITIALIZES ALL THE CONTROLS IN THE WORKSPACE
    private void initLayout() {
        // FIRST LOAD THE FONT FAMILIES FOR THE COMBO BOX
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        // THIS WILL BUILD ALL OF OUR JavaFX COMPONENTS FOR US
        AppNodesBuilder tdlBuilder = app.getGUIModule().getNodesBuilder();
        
	// THIS HOLDS ALL THE CONTROLS IN THE WORKSPACE
	VBox toDoListPane           = tdlBuilder.buildVBox(TDLM_PANE,                null,           null,   CLASS_TDLM_BOX, HAS_KEY_HANDLER,              FOCUS_TRAVERSABLE,      ENABLED);
        Label toDoListLabel         = tdlBuilder.buildLabel(TDLM_TO_DO_LIST_LABEL,   toDoListPane,   null,   CLASS_TDLM_BIG_HEADER, HAS_KEY_HANDLER,       FOCUS_TRAVERSABLE,      ENABLED);

        // THIS HAS THE DETAILS PANE COMPONENTS
        HBox nameOwnerPane          = tdlBuilder.buildHBox(TDLM_NAME_OWNER_PANE,       toDoListPane,   null,   CLASS_TDLM_BOX, HAS_KEY_HANDLER,             FOCUS_TRAVERSABLE,      ENABLED);
        HBox ownerPane              = tdlBuilder.buildHBox(TDLM_OWNER_PANE,            nameOwnerPane,  null,   CLASS_TDLM_BOX, HAS_KEY_HANDLER,             FOCUS_TRAVERSABLE,      ENABLED);
        Label ownerLabel            = tdlBuilder.buildLabel(TDLM_OWNER_LABEL,          ownerPane,      null,   CLASS_TDLM_PROMPT, HAS_KEY_HANDLER,          FOCUS_TRAVERSABLE,      ENABLED);
        TextField ownerTextField    = tdlBuilder.buildTextField(TDLM_OWNER_TEXT_FIELD, ownerPane,      null,   CLASS_TDLM_TEXT_FIELD, NO_KEY_HANDLER,       FOCUS_TRAVERSABLE,      ENABLED);
        HBox nameNamePane           = tdlBuilder.buildHBox(TDLM_NAME_NAME_PANE,        toDoListPane,   null,   CLASS_TDLM_BOX, HAS_KEY_HANDLER,             FOCUS_TRAVERSABLE,      ENABLED);
        HBox namePane               = tdlBuilder.buildHBox(TDLM_NAME_PANE,             nameOwnerPane,  null,   CLASS_TDLM_BOX, HAS_KEY_HANDLER,             FOCUS_TRAVERSABLE,      ENABLED);
        Label nameLabel             = tdlBuilder.buildLabel(TDLM_NAME_LABEL,           namePane,       null,   CLASS_TDLM_PROMPT, HAS_KEY_HANDLER,          FOCUS_TRAVERSABLE,      ENABLED);
        TextField nameTextField     = tdlBuilder.buildTextField(TDLM_NAME_TEXT_FIELD,  namePane,       null,   CLASS_TDLM_TEXT_FIELD, NO_KEY_HANDLER,       FOCUS_TRAVERSABLE,      ENABLED);
        
        // THIS HAS THE ITEMS PANE COMPONENTS
        VBox itemsPane              = tdlBuilder.buildVBox(TDLM_ITEMS_PANE,                   toDoListPane,       null,   CLASS_TDLM_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        HBox itemButtonsPane        = tdlBuilder.buildHBox(TDLM_ITEM_BUTTONS_PANE,            itemsPane,          null,   CLASS_TDLM_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        Button addItemButton        = tdlBuilder.buildIconButton(TDLM_ADD_ITEM_BUTTON,        itemButtonsPane,    null,   CLASS_TDLM_BUTTON, HAS_KEY_HANDLER,  FOCUS_TRAVERSABLE,  ENABLED);
        Button removeItemButton     = tdlBuilder.buildIconButton(TDLM_REMOVE_ITEM_BUTTON,     itemButtonsPane,    null,   CLASS_TDLM_BUTTON, HAS_KEY_HANDLER,  FOCUS_TRAVERSABLE,  DISABLED);
        Button editItemButton       = tdlBuilder.buildIconButton(TDLM_EDIT_ITEM_BUTTON,       itemButtonsPane,    null,   CLASS_TDLM_BUTTON, HAS_KEY_HANDLER,  FOCUS_TRAVERSABLE,  ENABLED);
        Button moveUpItemButton     = tdlBuilder.buildIconButton(TDLM_MOVE_UP_ITEM_BUTTON,    itemButtonsPane,    null,   CLASS_TDLM_BUTTON, HAS_KEY_HANDLER,  FOCUS_TRAVERSABLE,  ENABLED);
        Button moveDownItemButton   = tdlBuilder.buildIconButton(TDLM_MOVE_DOWN_ITEM_BUTTON,  itemButtonsPane,    null,   CLASS_TDLM_BUTTON, HAS_KEY_HANDLER,  FOCUS_TRAVERSABLE,  ENABLED);

        // AND NOW THE TABLE
        TableView<ToDoItemPrototype> itemsTable  = tdlBuilder.buildTableView(TDLM_ITEMS_TABLE_VIEW,       itemsPane,          null,   CLASS_TDLM_TABLE, HAS_KEY_HANDLER,    FOCUS_TRAVERSABLE,  true);
        TableColumn categoryColumn      = tdlBuilder.buildTableColumn(  TDLM_CATEGORY_COLUMN,    itemsTable,         CLASS_TDLM_COLUMN);
        TableColumn descriptionColumn   = tdlBuilder.buildTableColumn(  TDLM_DESCRIPTION_COLUMN, itemsTable,         CLASS_TDLM_COLUMN);
        TableColumn startDateColumn     = tdlBuilder.buildTableColumn(  TDLM_START_DATE_COLUMN,  itemsTable,         CLASS_TDLM_COLUMN);
        TableColumn endDateColumn       = tdlBuilder.buildTableColumn(  TDLM_END_DATE_COLUMN,    itemsTable,         CLASS_TDLM_COLUMN);
        TableColumn assignedToColumn    = tdlBuilder.buildTableColumn(  TDLM_ASSIGNED_TO_COLUMN, itemsTable,         CLASS_TDLM_COLUMN);
        TableColumn completedColumn     = tdlBuilder.buildTableColumn(  TDLM_COMPLETED_COLUMN,   itemsTable,         CLASS_TDLM_COLUMN);

        // SPECIFY THE TYPES FOR THE COLUMNS
        categoryColumn.setCellValueFactory(     new PropertyValueFactory<String,    String>("category"));
        descriptionColumn.setCellValueFactory(  new PropertyValueFactory<String,    String>("description"));
        startDateColumn.setCellValueFactory(    new PropertyValueFactory<LocalDate, String>("startDate"));
        endDateColumn.setCellValueFactory(      new PropertyValueFactory<LocalDate, String>("endDate"));
        assignedToColumn.setCellValueFactory(   new PropertyValueFactory<String,    String>("assignedTo"));
        completedColumn.setCellValueFactory(    new PropertyValueFactory<Boolean,   String>("completed"));

	// AND PUT EVERYTHING IN THE WORKSPACE
	workspace = new BorderPane();
	((BorderPane)workspace).setCenter(toDoListPane);

        // AND NOW SETUP ALL THE EVENT HANDLING CONTROLLERS
        ownerTextField.textProperty().addListener(e->{
            app.getFileModule().markAsEdited(true);
        });
        nameTextField.textProperty().addListener(e->{
            app.getFileModule().markAsEdited(true);
        });
        ItemsController itemsController = new ItemsController((ToDoListMakerApp)app);
        addItemButton.setOnAction(e->{
            itemsController.processAddItem();
        });
        removeItemButton.setOnAction(e->{
            itemsController.processRemoveItems();
        });
        editItemButton.setOnAction(e->{
            itemsController.processEditItem();
        });
        moveUpItemButton.setOnAction(e->{
            itemsController.processMoveUpItem();
        });
        moveDownItemButton.setOnAction(e->{
            itemsController.processMoveDownItem();
        });
        itemsTable.setOnMouseClicked(e -> {
            app.getFoolproofModule().updateAll();
            if(e.getClickCount()==2 && ((ToDoData)app.getDataComponent()).isItemSelected()){
                itemsController.processEditItem();
            }
        });
        ItemsTableController iTC = new ItemsTableController(app);
        itemsTable.widthProperty().addListener(e->{
            iTC.processChangeTableSize();
        });
        itemsTable.setOnSort(new EventHandler<SortEvent<TableView<ToDoItemPrototype>>>(){
            @Override
            public void handle(SortEvent<TableView<ToDoItemPrototype>> event) {
                ToDoData data = (ToDoData)app.getDataComponent();
                ArrayList<ToDoItemPrototype> oldListOrder = data.getCurrentItemsOrder();
                TableView view = event.getSource();
                ObservableList sortOrder = view.getSortOrder();
                if ((sortOrder != null) && (sortOrder.size() == 1)) {
                    TableColumn sortColumn = event.getSource().getSortOrder().get(0);
                    String columnText = sortColumn.getText();
                    SortType sortType = sortColumn.getSortType();
                    System.out.println("Sort by " + columnText);
                    event.consume();
                    SortItems_Transaction transaction = new SortItems_Transaction(data, oldListOrder, columnText, sortType);
                    app.processTransaction(transaction);
                    app.getFoolproofModule().updateAll();
                }
            }            
        });
    }
    
    public void initFoolproofDesign() {
        AppGUIModule gui = app.getGUIModule();
        AppFoolproofModule foolproofSettings = app.getFoolproofModule();
        foolproofSettings.registerModeSettings(TDLM_FOOLPROOF_SETTINGS, 
                new ToDoSelectionFoolproofDesign((ToDoListMakerApp)app));
    }

    @Override
    public void processWorkspaceKeyEvent(KeyEvent ke) {
       // System.out.println("WORKSPACE REPONSE TO " + ke.getCharacter());
    }
}