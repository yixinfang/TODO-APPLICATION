package model;

import utils.pojo.CSVCookBook;

import java.io.FileNotFoundException;

/**
 * Interface for model.
 */
public interface IModel {
    /**
     * Method in model that to process user's input and then add a new todo.
     *
     * @throws FileNotFoundException if the csv file cannot be found.
     */
    void setAddTodo() throws FileNotFoundException;


    /**
     * Method in model that to process user's input and then complete a specified todo.
     * A user can request the program perform completing multiple Todos by repeating the --complete-todo option
     * for each Todo to complete in one request.
     * E.g. --complete-todo 5 --complete-todo 2 would complete the Todo with ID 5 and the Todo with ID 2.
     */
    void setCompleted() throws FileNotFoundException;


    /**
     * Method in the process when user request to display a list of todos. By default, all of the
     * Todos should be printed but they can supply additional arguments to customize the list:
     * ○ Filter the list to only include incomplete Todos;
     * ○ Filter the list to only include Todos with a particular category;
     * ○ Sort the Todos by date (ascending) ;
     * ○ Sort the Todos by priority (ascending).
     * The two filter arguments can be combined but only one sort can be applied at a time.
     *
     * @throws FileNotFoundException if the csv file cannot be found.
     */
    void setDisplay() throws FileNotFoundException;

    /**
     * Getter to get csvCookBook.
     * @return
     */
    CSVCookBook getCsvCookBook();

}
