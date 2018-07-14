package djf.ui.foolproof;

import djf.ui.controllers.AppFileController;
import static djf.AppPropertyType.CLOSE_BUTTON;
import static djf.AppPropertyType.EXPORT_BUTTON;
import static djf.AppPropertyType.NEW_BUTTON;
import static djf.AppPropertyType.SAVE_BUTTON;
import djf.AppTemplate;
import djf.components.AppClipboardComponent;
import djf.modules.AppGUIModule;
import javafx.scene.control.Button;

/**
 *
 * @author McKillaGorilla
 */
public class FileFoolproofDesign implements FoolproofDesign {
    AppTemplate app;
    
    public FileFoolproofDesign(AppTemplate initApp) {
        app = initApp;
    }    

    @Override
    public void updateControls() {
        AppGUIModule gui = app.getGUIModule();
        
        Button newButton = (Button)gui.getGUINode(NEW_BUTTON);
        newButton.setDisable(false);
        Button closeButton = (Button)gui.getGUINode(CLOSE_BUTTON);
        closeButton.setDisable(!app.getWorkspaceComponent().isActivated());
        Button saveButton = (Button)gui.getGUINode(SAVE_BUTTON);
        saveButton.setDisable(app.getFileModule().isSaved());
        Button exportButton = (Button)gui.getGUINode(EXPORT_BUTTON);
        exportButton.setDisable(!app.getWorkspaceComponent().isActivated());
    }
}