import controller.Controller;
import controller.IController;
import model.IModel;
import model.Model;
import view.ArgParser;
import view.IView;
import view.View;

import java.io.FileNotFoundException;
import java.util.Map;

/**
 * This class represents the interface for user to give command line arguments and execute the MVC.
 */
public class App {
    private static final String addTodo = "--add-todo";
    private static final String completeTodo = "--complete-todo";
    private static final String display = "--display";
    public static String root = "generatedCSVs";

    /**
     * A user can request the program perform all three tasks (add, complete, and display) in one run
     * of the program. They may only add one Todo at a time, but they may complete multiple Todos
     * by repeating the --complete-todo option for each Todo to complete.
     *
     * @param args - given command line arguments
     * @throws FileNotFoundException if the csv file cannot be found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        ArgParser argParser = new ArgParser(args);
        Map<String, String> arguments = argParser.getArguments();
        IModel model = new Model(arguments);
        IView view = new View(arguments);
        IController controller = new Controller(model, view);
        if (arguments.containsKey(addTodo)) {
            controller.callAddTodo();
        }
        if (arguments.containsKey(completeTodo)) {
            controller.callComplete();
        }
        if (arguments.containsKey(display)) {
            controller.callDisplay();
        }
    }
}

