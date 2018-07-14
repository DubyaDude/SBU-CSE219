package djf.ui.controllers;

import djf.AppTemplate;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author McKillaGorilla
 */
public class AppKeyController implements EventHandler<KeyEvent> {
    AppTemplate app;
    
    public AppKeyController(AppTemplate initApp) {
        app = initApp;
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            if (isPressedWithCtrl(event, KeyCode.N)) {
                app.getGUIModule().getFileController().processNewRequest();
            }
            else if (isPressedWithCtrl(event, KeyCode.O)) {
                app.getGUIModule().getFileController().processLoadRequest();
            }
            else if (isPressedWithCtrl(event, KeyCode.S)) {
                app.getGUIModule().getFileController().processSaveRequest();
            }
            else if (isPressedWithCtrl(event, KeyCode.X)) {
                app.getClipboardComponent().cut();
            }
            else if (isPressedWithCtrl(event, KeyCode.C)) {
                app.getClipboardComponent().copy();
            }
            else if (isPressedWithCtrl(event, KeyCode.V)) {
                app.getClipboardComponent().paste();
            }
            else if (isPressedWithCtrl(event, KeyCode.Z)) {
                app.getGUIModule().getUndoController().processUndoRequest();
            }
            else if (isPressedWithCtrl(event, KeyCode.N)) {
                app.getGUIModule().getUndoController().processRedoRequest();
            }
        }

        // AND GIVE THE WORKSPACE A GO AT IT
        app.getWorkspaceComponent().processWorkspaceKeyEvent(event);
        
        // MAKE SURE THIS EVENT ISN'T PROCESSED TWICE
        event.consume();
    }    
    
    public boolean isPressedWithCtrl(KeyEvent event, KeyCode code) {
        return event.isControlDown() && (event.getCode() == code);
    }
}