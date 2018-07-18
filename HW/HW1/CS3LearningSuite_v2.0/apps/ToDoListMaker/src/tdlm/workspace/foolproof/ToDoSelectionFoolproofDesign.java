package tdlm.workspace.foolproof;

import djf.modules.AppGUIModule;
import djf.ui.foolproof.FoolproofDesign;
import javafx.scene.control.TextField;
import tdlm.ToDoListMakerApp;
import static tdlm.ToDoPropertyType.TDLM_EDIT_ITEM_BUTTON;
import static tdlm.ToDoPropertyType.TDLM_MOVE_DOWN_ITEM_BUTTON;
import static tdlm.ToDoPropertyType.TDLM_MOVE_UP_ITEM_BUTTON;
import static tdlm.ToDoPropertyType.TDLM_OWNER_TEXT_FIELD;
import static tdlm.ToDoPropertyType.TDLM_NAME_TEXT_FIELD;
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
        ToDoData data = (ToDoData) app.getDataComponent();
        boolean itemIsSelected = data.isItemSelected();
        boolean itemsAreSelected = data.areItemsSelected();
        boolean itemIsBottom = data.isItemBottom();
        boolean itemIsTop = data.isItemTop();
        gui.getGUINode(TDLM_REMOVE_ITEM_BUTTON).setDisable(!(itemIsSelected || itemsAreSelected));
        gui.getGUINode(TDLM_MOVE_UP_ITEM_BUTTON).setDisable(!(itemIsSelected && !itemIsTop));
        gui.getGUINode(TDLM_MOVE_DOWN_ITEM_BUTTON).setDisable(!(itemIsSelected && !itemIsBottom));
        gui.getGUINode(TDLM_EDIT_ITEM_BUTTON).setDisable(!(itemIsSelected));
        ((TextField) gui.getGUINode(TDLM_OWNER_TEXT_FIELD)).setEditable(true);
        ((TextField) gui.getGUINode(TDLM_NAME_TEXT_FIELD)).setEditable(true);

    }
}
