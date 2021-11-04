package todo;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ManageSystemTest {
    Todo todo1 = new Todo("Coding", "true", null, 1, "I like it");
    Todo todo2 = new Todo("Make-up", "false", null, 2, "I like it");
    Todo todo3 = new Todo("Running", "false", null, 2, "Keep going");
    Todo todo4 = new Todo("Fitness", "true", null, 3, "Keep going");
    ManageSystem manageSystem = new ManageSystem();

    @Test
    public void testAddNewTodo() throws IOException {
        manageSystem.addTodo(todo1, "out");
        manageSystem.addTodo(todo2, "out");
        manageSystem.addTodo(todo3, "out");
        manageSystem.addTodo(todo4, "out");
    }

    @Test
    public void completeTodo() {
    }
}