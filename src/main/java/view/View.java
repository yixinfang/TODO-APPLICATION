package view;

//import pojo.CSVCookBook;
import utils.exception.IllegalArgComboException;
import utils.modules.CSVReader;
import utils.pojo.CSVCookBook;


import javax.swing.text.DateFormatter;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class represents the view for this TODO application system.
 */
public class View implements IView{
    private static final String CSVFile = "--csv-file";
    private static final String showIncomplete = "--show-incomplete";
    private static final String showCategory = "--show-category";
    private static final String sortByDate = "--sort-by-date";
    private static final String sortByPriority = "--sort-by-priority";
    public static final String root = "generatedCSVs";
    private static final Integer ONE = 1;

    private Map<String, String> arguments;

    /**
     * Construct a new view.
     * @param arguments - the parsed user's input.
     */
    public View(Map<String, String> arguments) {
        this.arguments = arguments;
    }

    /**
     * Method to display the list of todos.
     * @param csvCookBookFromModel - csvCookBook generated from Model
     */
    @Override
    public void Display(CSVCookBook csvCookBookFromModel) {
        StringBuilder sb = new StringBuilder("Ready to display the todos in " + arguments.get(CSVFile) +",");
        if (arguments.containsKey(showIncomplete)) {
            sb.append(" incomplete,");
        }
        if (arguments.containsKey(showCategory)) {
            sb.append(" the category is \"").append(arguments.get(showCategory)).append("\", ");
        }
        if (arguments.containsKey(sortByPriority)) {
            sb.append(" sorted by priority,");
        }
        if (arguments.containsKey(sortByDate)) {
            sb.append(" sorted by date,");
        }
        System.out.println(sb.deleteCharAt(sb.length() - ONE).append(":").append("\n").toString()
                + csvCookBookFromModel.toString());
    }

}
