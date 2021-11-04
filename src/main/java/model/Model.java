package model;

import utils.exception.IllegalArgComboException;
import utils.modules.CSVReader;
import utils.pojo.CSVCookBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents the model for this TODO application system.
 */
public class Model implements IModel {

    private static final String csvFile = "--csv-file";
    private static final String addTodo = "--add-todo";
    private static final String todoText = "--todo-text";
    private static final String completed = "--completed";
    private static final String due = "--due";
    private static final String priority = "--priority";
    private static final String category = "--category";
    private static final String completeTodo = "--complete-todo";
    private static final String display = "--display";
    private static final String showIncomplete = "--show-incomplete";
    private static final String showCategory = "--show-category";
    private static final String sortByDate = "--sort-by-date";
    private static final String sortByPriority = "--sort-by-priority";
    private static final String Empty = "";
    private static final Integer THREE = 3;
    private static final Integer ONE = 1;
    private static final Integer ZERO = 0;


    public static final String root = "generatedCSVs";


    private Map<String, String> arguments;
    private CSVCookBook csvCookBook;

    /**
     * Constructor of Model.
     * @param arguments - the parsed input from customer.
     */
    public Model(Map<String, String> arguments) {
        this.arguments = arguments;
    }

    /**
     * Method in model that to process user's input and then add a new todo.
     * A user can only request the program perform adding one Todo at a time in one run of the program.
     *
     * @throws FileNotFoundException if the csv file cannot be found.
     */
    @Override
    public void setAddTodo() throws FileNotFoundException {
        String textToBeFilled = arguments.get(todoText);
        String toBeCompleted = (arguments.containsKey(completed)) ? "true" : "false";
        Date dueDateToBeFilled = null;
        Integer priorityToBeFilled = THREE;
        String categoryToBeFilled = Empty;
        if (arguments.containsKey(due)) {
            if (isValidDueGiven(arguments.get(due))) {
                try {
                    dueDateToBeFilled = new SimpleDateFormat("MM/dd/yyyy").parse(arguments.get(due));
                } catch (ParseException pe) {
                    pe.printStackTrace();
                }
            } else {
                throw new IllegalArgComboException("Please provide the date as the format MM/dd/yyyy");
            }
        }

        if (arguments.containsKey(priority)) {
            if(!arguments.get(priority).isEmpty()) {
                if (Integer.parseInt(arguments.get(priority)) <= THREE && Integer.parseInt(arguments.get(priority)) >= ONE) {
                    priorityToBeFilled = Integer.parseInt(arguments.get(priority));
                } else {
                    throw new IllegalArgComboException("If choosing to specify a priority, it must be 1, 2, or 3, " +
                            "with 1 being the highest priority.\n"
                            + "If no priority is specified, the todo can be treated as lowest priority (i.e., 3)");
                }
            }
        }

        if (arguments.containsKey(category)) {
            if (arguments.get(category).startsWith("--")) {
                throw new IllegalArgComboException(" The value of category can not be started with \"--\"");
            } else {
                categoryToBeFilled = arguments.get(category);
            }
        }
        Todo newTodo = new Todo(textToBeFilled, toBeCompleted, dueDateToBeFilled, priorityToBeFilled, categoryToBeFilled);
        String fileName = root + File.separatorChar + arguments.get(csvFile) + ".csv";
        ModelAdaptor modelAdaptor = (!new File(fileName.trim()).isFile())
                ? new ModelAdaptor()
                : new ModelAdaptor(CSVReader.readContent(fileName));
        try {
            modelAdaptor.addTodo(newTodo, arguments.get(csvFile));
        } catch (IOException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }

    /**
     * Method to check if the given due date is valid.
     * @param inDate - the given due date.
     * @return true if valid.
     */
    private static boolean isValidDueGiven(String inDate) {
        if (inDate.isEmpty()) {
            return false;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Method in model that to process user's input and then complete a specified todo.
     * A user can request the program perform completing multiple Todos by repeating the --complete-todo option
     * for each Todo to complete in one request.
     * E.g. --complete-todo 5 --complete-todo 2 would complete the Todo with ID 5 and the Todo with ID 2.
     *
     * @throws FileNotFoundException if the csv file cannot be found.
     */
    @Override
    public void setCompleted() throws FileNotFoundException {
        String fileName = root + File.separatorChar + arguments.get(csvFile) + ".csv";
        if (!new File(fileName.trim()).isFile()) {
            throw new IllegalArgComboException("Cannot find the csv File");
        }
        ModelAdaptor modelAdaptor = new ModelAdaptor(CSVReader.readContent(fileName));
        String completeTodoList = arguments.get(completeTodo);
        String[] completeTodoArray = doubleQuoteHandler(completeTodoList); // can request multiple IDs, so the input is list of IDs
        for (String id : completeTodoArray) {
            try {
                modelAdaptor.completeTodo(Integer.parseInt(id), arguments.get(csvFile));
            } catch (IllegalArgComboException e) {
                System.out.println("Invalid Id is given");
                throw new IllegalArgComboException("Invalid Id is given");
            } catch (FileNotFoundException fnfe) {
                System.out.println("File Not FOUND.");
            }
        }
    }

    private static final String splitter = "\",\"";
    private static final String letterToRemove = "\"";
    private static final String letterToReplace = "";

    /**
     * This helper method serves to convert the String format of the id list (may contain multiple ids)
     * into array of single character, by replacing target letter and splitting a integrate String into several entries.
     *
     * @param tmp - the String format of the id list.
     * @return - An array of single character.
     */
    private static String[] doubleQuoteHandler(String tmp) {
        String[] curr = tmp.split(splitter);
        curr[ZERO] = curr[ZERO].replace(letterToRemove, letterToReplace);
        curr[curr.length - ONE] = curr[curr.length - ONE].replace(letterToRemove, letterToReplace);
        return curr;
    }

    /**
     * Method in the process when user request to display a list of todos. By default, all of the
     * Todos should be printed but they can supply additional arguments to customize the list:
     * This method also provides setter for the filed csvCookBook.
     *
     * @throws FileNotFoundException if the csv file cannot be found.
     */
    @Override
    public void setDisplay() throws FileNotFoundException {
        String fileName = root + File.separatorChar + arguments.get(csvFile) + ".csv";
        if (!new File(fileName.trim()).isFile()) {
            throw new IllegalArgComboException("Cannot find the csv File to display");
        }
        ModelAdaptor modelAdaptor = new ModelAdaptor(CSVReader.readContent(fileName));
        CSVCookBook csvCookBookTarget = CSVReader.readContent(fileName);
        LinkedHashMap<String, Integer> headersTarget = csvCookBookTarget.getHeaders();
        List destList = csvCookBookTarget.getContents();
        if (arguments.containsKey(showIncomplete)) {
            destList = modelAdaptor.displayTodos(destList,showIncomplete, Empty);
        }
        if (arguments.containsKey(showCategory)) {
            destList = modelAdaptor.displayTodos(destList, showCategory, arguments.get(showCategory));
        }
        if (arguments.containsKey(sortByPriority)) {
            destList = modelAdaptor.displayTodos(destList, sortByPriority, Empty);
        }
        if (arguments.containsKey(sortByDate)) {
            destList = modelAdaptor.displayTodos(destList, sortByDate, Empty);
        }
        this.csvCookBook = new CSVCookBook(headersTarget,destList);
    }

    /**
     * Getter to get csvCookBook.
     * @return
     */
    public CSVCookBook getCsvCookBook() {
        return csvCookBook;
    }
}
