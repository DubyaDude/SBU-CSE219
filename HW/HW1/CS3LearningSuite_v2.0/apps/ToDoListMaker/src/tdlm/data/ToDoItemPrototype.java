package tdlm.data;

import java.time.LocalDate;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author McKillaGorilla
 */
public class ToDoItemPrototype implements Cloneable {
    public static final String DEFAULT_CATEGORY = "?";
    public static final String DEFAULT_DESCRIPTION = "?";
    public static final LocalDate DEFAULT_DATE = LocalDate.now();
    public static final String DEFAULT_ASSIGNEDTO = "?";
    public static final boolean DEFAULT_COMPLETED = false;
    
    final StringProperty category;
    final StringProperty description;
    final ObjectProperty<LocalDate> startDate;
    final ObjectProperty<LocalDate> endDate;
    final StringProperty assignedTo;
    final BooleanProperty completed;
       
    public ToDoItemPrototype() {
        category = new SimpleStringProperty(DEFAULT_CATEGORY);
        description = new SimpleStringProperty(DEFAULT_DESCRIPTION);
        startDate = new SimpleObjectProperty(DEFAULT_DATE);
        endDate = new SimpleObjectProperty(DEFAULT_DATE);
        assignedTo = new SimpleStringProperty(DEFAULT_ASSIGNEDTO);
        completed = new SimpleBooleanProperty(DEFAULT_COMPLETED);
    }

    public ToDoItemPrototype(String initCategory, String initDescription, LocalDate initStartDate, LocalDate initEndDate, String initAssignedTo, boolean initCompleted) {
        this();
        category.set(initCategory);
        description.set(initDescription);
        startDate.set(initStartDate);
        endDate.set(initEndDate);
        assignedTo.set(initAssignedTo);
        completed.set(initCompleted);
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String value) {
        category.set(value);
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String value) {
        description.set(value);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate.get();
    }

    public void setStartDate(LocalDate value) {
        startDate.set(value);
    }

    public ObjectProperty startDateProperty() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate.get();
    }

    public void setEndDate(LocalDate value) {
        endDate.set(value);
    }

    public ObjectProperty endDateProperty() {
        return endDate;
    }
    
    public String getAssignedTo() {
        return assignedTo.get();
    }

    public void setAssignedTo(String value) {
        assignedTo.set(value);
    }

    public StringProperty assignedToProperty() {
        return assignedTo;
    }

    public boolean isCompleted() {
        return completed.get();
    }

    public void setCompleted(boolean value) {
        completed.set(value);
    }

    public BooleanProperty completedProperty() {
        return completed;
    }
    
    public void reset() {
        setCategory(DEFAULT_CATEGORY);
        setDescription(DEFAULT_DESCRIPTION);
        setStartDate(DEFAULT_DATE);
        setEndDate(DEFAULT_DATE);
        setAssignedTo(DEFAULT_ASSIGNEDTO);
        setCompleted(DEFAULT_COMPLETED);
    }

    public Object clone() {
        return new ToDoItemPrototype(   category.getValue(), 
                                        description.getValue(), 
                                        startDate.getValue(), 
                                        endDate.getValue(),
                                        assignedTo.getValue(),
                                        completed.getValue());
    }
    
    public boolean equals(Object obj) {
        return this == obj;
    }
}