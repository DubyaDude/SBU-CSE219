package tdlm.transactions;

import java.util.ArrayList;
import java.util.Comparator;
import javafx.scene.control.TableColumn.SortType;
import jtps.jTPS_Transaction;
import properties_manager.PropertiesManager;
import static tdlm.ToDoPropertyType.TDLM_CATEGORY_COLUMN;
import static tdlm.ToDoPropertyType.TDLM_DESCRIPTION_COLUMN;
import static tdlm.ToDoPropertyType.TDLM_START_DATE_COLUMN;
import tdlm.data.ToDoData;
import tdlm.data.ToDoItemPrototype;

/**
 *
 * @author McKillaGorilla
 */
public class SortItems_Transaction implements jTPS_Transaction {
    ToDoData data;
    ArrayList<ToDoItemPrototype> oldListOrder;
    ArrayList<ToDoItemPrototype> newListOrder;
    String sortingCriteria;
    SortType sortType;
    Comparator sortComparator;

    public SortItems_Transaction(   ToDoData initData, 
                                    ArrayList<ToDoItemPrototype> initOldListOrder, 
                                    String initSortingCriteria,
                                    SortType initSortType) {
        data = initData;
        oldListOrder = initOldListOrder;
        sortingCriteria = initSortingCriteria;
        sortType = initSortType;
        sortComparator = new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                ToDoItemPrototype tD1 = (ToDoItemPrototype)o1;
                ToDoItemPrototype tD2 = (ToDoItemPrototype)o2;
                Comparable c1, c2;
                PropertiesManager props = PropertiesManager.getPropertiesManager();
                if (sortingCriteria.equals(props.getProperty(TDLM_CATEGORY_COLUMN + "_TEXT"))) {
                    c1 = tD1.getCategory();
                    c2 = tD2.getCategory();
                }
                else if (sortingCriteria.equals(props.getProperty(TDLM_DESCRIPTION_COLUMN + "_TEXT"))) {
                    c1 = tD1.getDescription();
                    c2 = tD2.getDescription();
                }
                else if (sortingCriteria.equals(props.getProperty(TDLM_START_DATE_COLUMN+"_TEXT"))) {
                    c1 = tD1.getStartDate();
                    c2 = tD2.getStartDate();
                }
                else {
                    c1 = tD1.completedProperty().toString();
                    c2 = tD2.completedProperty().toString();
                }
                if (sortType == SortType.ASCENDING) {
                    return c1.compareTo(c2);
                }
                else {
                    return c2.compareTo(c1);
                }
            }
        };
    }

    @Override
    public void doTransaction() {
        data.sortItems(sortComparator);
        newListOrder = data.getCurrentItemsOrder();
    }

    @Override
    public void undoTransaction() {
        data.rearrangeItems(oldListOrder);
    }    
}