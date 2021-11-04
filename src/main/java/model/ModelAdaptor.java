package model;

import utils.exception.IllegalArgComboException;
import utils.modules.CSVProcessor;
import utils.pojo.CSVCookBook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class represents an adaptor for model to further process the data within the CSVCookBook as request.
 */
public class ModelAdaptor {
    private static final String id = "id";
    private static final String text = "text";
    private static final String completed = "completed";
    private static final String due = "due";
    private static final String priority = "priority";
    private static final String category = "category";
    private static final String Empty = "";
    private static final String FALSE = "false";
    private static final String PRIORITY = "priority";
    private static ArrayList<String> entryInHeaders = new ArrayList<String>() {{
        add(id);
        add(text);
        add(completed);
        add(due);
        add(priority);
        add(category);
    }};

    private CSVCookBook csvCookBook = new CSVCookBook(new LinkedHashMap<>());


    /**
     * Constructor applied if the CSVCookBook data can be obtained from the existing CSV source.
     * @param csvCookBook the given CSVCookBook data.
     */
    public ModelAdaptor(CSVCookBook csvCookBook) {
        this.csvCookBook = csvCookBook;
    }

    /**
     * Constructor applied if the no data is available,
     * i.e., since the given CSV file name is firstly introduced, it is necessary to create a new CSVCookBook
     * from scratch.
     */
    public ModelAdaptor() {
        this.csvCookBook.setHeaders(CustomerHeader());
        this.csvCookBook.setContents(new ArrayList<>());
    }

    /**
     * Help method to prepare the headers for a new generating CSVCookBook.
     * @return the Map<String, String> headers.
     */
    private LinkedHashMap<String, Integer> CustomerHeader() {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < entryInHeaders.size(); i++) {
            map.put(entryInHeaders.get(i), i);
        }
        return map;
    }

    /**
     * Method to add a new todo. The user must supply the information required by the Todo data structure.
     * They can also choose to specify the optional information.
     * When a new Todo is added, the CSV file should be updated.
     * @param todo the new todo to be added.
     * @param filePath - the CSV file path to be added with a new todo.
     * @throws IOException - ioexception.
     */
    public void addTodo(Todo todo, String filePath) throws IOException {
        String[] tmpArray = new String[entryInHeaders.size()];
        tmpArray[this.csvCookBook.getHeaders().get(id)] = ((Integer) (this.csvCookBook.getContents().size() + 1)).toString();
        tmpArray[this.csvCookBook.getHeaders().get(text)] = todo.getText();
        tmpArray[this.csvCookBook.getHeaders().get(completed)] = todo.isCompleted();
        Format f = new SimpleDateFormat("MM/dd/yyyy");
        tmpArray[this.csvCookBook.getHeaders().get(due)] = (todo.getDue() == null) ? "" : f.format(todo.getDue());
        tmpArray[this.csvCookBook.getHeaders().get(priority)] = todo.getPriority().toString();
        tmpArray[this.csvCookBook.getHeaders().get(category)] = todo.getCategory();
        this.csvCookBook.getContents().add(tmpArray);
        CSVProcessor.csvAppender(this.csvCookBook.getContents().size(), tmpArray,filePath);
    }

    /**
     * Method to complete an existing todo. The user set the completed status of an existing Todo to
     * true. When the status is changed, the CSV file should be updated.
     * @param id - the given id of the target todo.
     * @param filePath - the CSV file path.
     * @throws FileNotFoundException if the csv file cannot be found.
     */
    public void completeTodo(int id, String filePath) throws FileNotFoundException {
        if (id <= this.csvCookBook.getContents().size() && id > 0) {
            this.csvCookBook.getContents().get(id - 1)[this.csvCookBook.getHeaders().get(completed)] = "true";
            CSVProcessor.printCSVCookBook(this.csvCookBook, filePath);
        } else {
            throw new IllegalArgComboException("Invalid Id is given.");
        }
    }

    /**
     * Method to process the data within the csvCookBook if requesting display.
     * @param destList - the contents in the csvCookBook
     * @param option - the following option.
     * @param argument - the following argument if given.
     * @return - a new list of todos.
     */
    public List<String[]> displayTodos(List<String[]> destList, String option, String argument) {
        if (option.equals("--show-incomplete")) {
            destList = filter(destList, completed, FALSE);
        }
        if (option.equals("--show-category")) {
            destList = filter(destList, category, argument);
        }
        if (option.equals("--sort-by-priority")) {
            destList = sortByOption(priority, destList);
        }
        if (option.equals("--sort-by-date")) {
            destList = sortByDate(destList, due);
        }
        return destList;
    }

    /**
     * Helper method to filter the list of todos as request.
     * @param sourceList - the list of todos to be filtered.
     * @param key - the entry name.
     * @param arg - the content in the entry.
     * @return a new list of todo.
     */
    private List<String[]> filter(List<String[]> sourceList, String key, String arg) {
        List<String[]> tmpList;
        tmpList = sourceList
                .stream()
                .filter(arr -> arr[this.csvCookBook.getHeaders().get(key)].equals(arg))
                .collect(Collectors.toList());
        return tmpList;
    }

    /**
     * Helper method to sort the list of todos as request.
     * @param key - the entry name.
     * @param updatedContents - the list of todos to be sorted.
     * @return a new list of todo.
     */
    private List<String[]> sortByOption(String key, List<String[]> updatedContents) {
        updatedContents.sort((o1, o2) -> {
            if (o1[this.csvCookBook.getHeaders().get(key)] == null || o2[this.csvCookBook.getHeaders().get(key)] == null) {
                return 0;
            }
            return o1[this.csvCookBook.getHeaders().get(key)].compareTo(o2[this.csvCookBook.getHeaders().get(key)]);
        });
        return updatedContents;
    }

    /**
     * Helper method to sort the due date in the list of todos.
     * @param updatedContents - the list of todos to be sorted.
     * @param key - the entry name.
     * @return a new list of todo.
     */
    private List<String[]> sortByDate(List<String[]> updatedContents,String key) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        Integer dueIndex = this.csvCookBook.getHeaders().get(key);
        updatedContents.sort((Comparator.comparing(o -> (o[dueIndex] != null && !o[dueIndex].equals(Empty))
                ? LocalDate.parse(o[dueIndex], format) : LocalDate.MAX)));
        return updatedContents;
    }
}
