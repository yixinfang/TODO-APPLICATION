package view;

import utils.pojo.CSVCookBook;


/**
 * Interface for View.
 */
public interface IView {
    /**
     * Method to display the list of todos.
     * @param csvCookBookFromModel - csvCookBook generated from Model
     */
    void Display(CSVCookBook csvCookBookFromModel);
}
