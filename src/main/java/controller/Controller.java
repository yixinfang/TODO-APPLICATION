package controller;

import model.IModel;
import view.IView;

import java.io.FileNotFoundException;

/**
 * This class represents the controller for this TODO application system.
 */
public class Controller implements IController {
    private IModel model;
    private IView view;

    public Controller(IModel model, IView view) {
        this.model = model;
        this.view = view;
    }

    public void callAddTodo() throws FileNotFoundException {
        this.model.setAddTodo();
    }

    public void callComplete() throws FileNotFoundException {
        this.model.setCompleted();
    }

    public void callDisplay() throws FileNotFoundException {
        this.model.setDisplay();
        this.view.Display(this.model.getCsvCookBook());
    }

//    public void callAddTodo() throws FileNotFoundException {
//        if (arguments.containsKey(todoText)) {
//            if(!new File(fileName.trim()).isFile()) { // if the specific CSV file can not be found in the target root directory, write (create) a new CSV file.
//                Model model = new Model(arguments, new ManageSystem());
//                model.setAddTodo();
//              //  Model.setAddTodo(arguments, new ManageSystem()); // create an new manegeSystem with empty contents
//            } else {
//                Model model = new Model(arguments, new ManageSystem(CSVReader.readContent(fileName)));
//                model.setAddTodo();
//              //  Model.setAddTodo(arguments, new ManageSystem(CSVReader.readContent(fileName)));
//            }
//        } else {
//            throw new IllegalArgComboException("If the option \"--add-todo\" is provided, "
//                    + "then --todo-text must also be provided.");
//        }
//    }

//    public void callComplete() throws FileNotFoundException {
//        if (!new File(fileName.trim()).isFile()) {
//            throw new IllegalArgComboException("Cannot find the csv File");
//        }
//        Model model = new Model(arguments, new ManageSystem(CSVReader.readContent(fileName)));
//        model.setCompleted();
//       // Model.setCompleteTodo(arguments, new ManageSystem(CSVReader.readContent(fileName)));
//    }

//    public void callDisplay() throws FileNotFoundException {
//        if (!new File(fileName.trim()).isFile()) {
//            throw new IllegalArgComboException("Cannot find the csv File to display");
//        }
//        View.setDisplay(arguments, new ManageSystem(CSVReader.readContent(fileName)));
//    }




}

