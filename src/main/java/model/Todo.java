package model;

import java.util.Date;
import java.util.Objects;

/**
 * Todo is a class that contain text, completed status, due,
 * priority, and category
 *
 */
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
     * @return the text of Todo.
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @return the completion status of Todo.
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Todo)) return false;
        Todo todo = (Todo) o;
        return Objects.equals(getText(), todo.getText()) && Objects.equals(completed, todo.completed) && Objects.equals(getDue(), todo.getDue()) && Objects.equals(getPriority(), todo.getPriority()) && Objects.equals(getCategory(), todo.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getText(), completed, getDue(), getPriority(), getCategory());
    }

    @Override
    public int compareTo(Todo o) {
        return getDue().compareTo(o.getDue());
    }

    @Override
    public String toString() {
        return "Todo{" +
                "text='" + text + '\'' +
                ", completed='" + completed + '\'' +
                ", due=" + due +
                ", priority=" + priority +
                ", category='" + category + '\'' +
                '}';
    }
}
