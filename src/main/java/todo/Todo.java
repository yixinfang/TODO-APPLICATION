package todo;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Todo implements Comparable<Todo>{
    private final String text;
    private String completed;
    private final Date due;
    private final Integer priority;
    private final String category;

    /**
     * Construct a new Todo class
     * @param text the text of the new Todo
     * @param completed the status of the completion of the new Todo
     * @param due the due date of the new Todo
     * @param customPriority the priority of the new Todo
     * @param category the category of the new Todo
     */
    public Todo(String text, String completed, Date due, Integer customPriority, String category) {
        this.text = text;
        this.completed = completed;
        this.due = due;
        this.priority = customPriority;
        this.category = category;
    }

    /**
     *
     * @return the text of Todo
     */
    public String getText() {
        return text;
    }
    /**
     *
     * @return the completion status of Todo
     */
    public String isCompleted() {
        return completed;
    }
    /**
     *
     * @return the due date of Todo
     */
    public Date getDue() {
        return due;
    }
    /**
     *
     * @return the priority of Todo
     */
    public Integer getPriority() {
        return priority;
    }
    /**
     *
     * @return the category of Todo
     */
    public String getCategory() {
        return category;
    }

    /**
     * Set/change the priority of Todo
     * @param customInput the new priority we want to set
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private void setPriority(Integer customInput) throws NoSuchFieldException, IllegalAccessException {
        if (customInput >= 1 && customInput <= 3) {
            Field priority = Field.class.getDeclaredField("priority");
            priority.setAccessible(true);
            priority.setInt(priority, customInput);
        }
    }

    /**
     * set/change the completion status
     * @param completed the new completion status ("ture" or "false")
     */
    public void setCompleted(String completed) {
        this.completed = completed;
    }

    @Override
    public int compareTo(Todo o) {
        return getDue().compareTo(o.getDue());
    }

}
