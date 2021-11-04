package todo;


import utils.exception.IllegalArgComboException;

import java.io.IOException;
import java.util.List;

public interface IManageSystem<T> {
    void addTodo(T todo, String filePath) throws IOException;

    void completeTodo(int id, String filePath) throws IllegalArgComboException;

    List<String[]> displayTodos(List<String[]> destList, String option, String argument);
}
