package tdlm.workspace.foolproof;

import djf.modules.AppGUIModule;
import djf.ui.foolproof.FoolproofDesign;
import javafx.scene.control.TextField;
import tdlm.ToDoListMakerApp;
import static tdlm.ToDoPropertyType.TDLM_OWNER_TEXT_FIELD;
import static tdlm.ToDoPropertyType.TDLM_REMOVE_ITEM_BUTTON;
import tdlm.data.ToDoData;

/**
 *
 * @author McKillaGorilla
 */
public class ToDoSelectionFoolproofDesign implements FoolproofDesign {
    ToDoListMakerApp app;
    
    public ToDoSelectionFoolproofDesign(ToDoListMakerApp initApp) {
        app = initApp;
    }

    @Override
    public void updateControls() {
        AppGUIModule gui = app.getGUIModule();
       
        // CHECK AND SEE IF A TABLE ITEM IS SELECTED
        ToDoData data = (ToDoData)app.getDataComponent();
        boolean itemIsSelected = data.isItemSelected();
        boolean itemsAreSelected = data.areItemsSelected();
        gui.getGUINode(TDLM_REMOVE_ITEM_BUTTON).setDisable(!(itemIsSelected || itemsAreSelected));
        ((TextField)gui.getGUINode(TDLM_OWNER_TEXT_FIELD)).setEditable(true);
    }
}