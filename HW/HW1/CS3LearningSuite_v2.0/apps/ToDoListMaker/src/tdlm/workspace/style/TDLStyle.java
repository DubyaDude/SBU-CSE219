package tdlm.workspace.style;

/**
 * This class lists all CSS style types for this application. These
 * are used by JavaFX to apply style properties to controls like
 * buttons, labels, and panes.

 * @author Richard McKenna
 * @author ?
 * @version 1.0
 */
public class TDLStyle {
    public static final String EMPTY_TEXT = "";
    public static final int BUTTON_TAG_WIDTH = 75;

    // THESE CONSTANTS ARE FOR TYING THE PRESENTATION STYLE OF
    // THIS M3Workspace'S COMPONENTS TO A STYLE SHEET THAT IT USES
    // NOTE THAT FOUR CLASS STYLES ALREADY EXIST:
    // top_toolbar, toolbar, toolbar_text_button, toolbar_icon_button
    
    public static final String CLASS_TDLM_PANE          = "tdlm_pane";
    public static final String CLASS_TDLM_BOX           = "tdlm_box";            
    public static final String CLASS_TDLM_BIG_HEADER    = "tdlm_big_header";
    public static final String CLASS_TDLM_SMALL_HEADER  = "tdlm_small_header";
    public static final String CLASS_TDLM_PROMPT        = "tdlm_prompt";
    public static final String CLASS_TDLM_TEXT_FIELD    = "tdlm_text_field";
    public static final String CLASS_TDLM_BUTTON        = "tdlm_button";
    public static final String CLASS_TDLM_TABLE         = "tdlm_table";
    public static final String CLASS_TDLM_COLUMN        = "tdlm_column";
    
    // STYLE CLASSES FOR THE ADD/EDIT ITEM DIALOG
    public static final String CLASS_TDLM_DIALOG_GRID           = "tdlm_dialog_grid";
    public static final String CLASS_TDLM_DIALOG_HEADER         = "tdlm_dialog_header";
    public static final String CLASS_TDLM_DIALOG_PROMPT         = "tdlm_dialog_prompt";
    public static final String CLASS_TDLM_DIALOG_TEXT_FIELD     = "tdlm_dialog_text_field";
    public static final String CLASS_TDLM_DIALOG_DATE_PICKER    = "tdlm_dialog_date_picker";
    public static final String CLASS_TDLM_DIALOG_CHECK_BOX      = "tdlm_dialog_check_box";
    public static final String CLASS_TDLM_DIALOG_BUTTON         = "tdlm_dialog_button";
    public static final String CLASS_TDLM_DIALOG_PANE           = "tdlm_dialog_pane";
}