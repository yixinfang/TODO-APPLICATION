package todo;


import utils.exception.IllegalArgComboException;
import utils.modules.CSVProcessor;
import utils.pojo.CSVCookBook;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ManageSystem<T extends Todo> implements IManageSystem<T> {
    private static final String id = "id";
    private static final String text = "text";
    private static final String completed = "completed";
    private static final String due = "due";
    private static final String priority = "priority";
    private static final String category = "category";
    private static ArrayList<String> entryInHeaders = new ArrayList<String>() {{
        add(id);
        add(text);
        add(completed);
        add(due);
        add(priority);
        add(category);
    }};

    private final LinkedHashMap<String, Integer> headers = new LinkedHashMap<>();
    private CSVCookBook csvCookBook = new CSVCookBook(headers);

    public ManageSystem(LinkedHashMap<String, Integer> headers) {
        this.csvCookBook.setHeaders(headers);
        this.csvCookBook.setContents(new ArrayList<>());
    }

    public ManageSystem() {
        setHeader();
        this.csvCookBook.setContents(new ArrayList<>());
    }


    public CSVCookBook getCsvCookBook() {
        return csvCookBook;
    }

    protected void setHeader() {
        for (int i = 0; i < entryInHeaders.size(); i++) {
            this.headers.put(entryInHeaders.get(i), i);
        }
        this.csvCookBook.setHeaders(this.headers);
    }

    @Override
    public void addTodo(T todo, String filePath) throws IOException {
        String[] tmpArray = new String[entryInHeaders.size()];
        tmpArray[headers.get(id)] = ((Integer) (this.csvCookBook.getContents().size() + 1)).toString();
        tmpArray[headers.get(text)] = todo.getText();
        tmpArray[headers.get(completed)] = todo.isCompleted();
        Format f = new SimpleDateFormat("MM/dd/yy");
        tmpArray[headers.get(due)] = (todo.getDue() == null) ? "" : f.format(todo.getDue());
        tmpArray[headers.get(priority)] = todo.getPriority().toString();
        tmpArray[headers.get(category)] = todo.getCategory();
        this.csvCookBook.getContents().add(tmpArray);
        CSVProcessor.csvAppender(this.csvCookBook.getContents().size(), tmpArray, filePath);
    }

    @Override
    public void completeTodo(int id, String filePath) {
        if (id <= this.csvCookBook.getContents().size() && id > 0) {
            csvCookBook.getContents().get(id - 1)[headers.get(completed)] = "true";
            CSVProcessor.printCSVCookBook(this.csvCookBook, filePath);
        } else {
            throw new IllegalArgComboException("Invalid Id is given");
        }

    }

    private List<String[]> filter(List<String[]> tmpList, String key, String arg) {
        tmpList = csvCookBook.getContents()
                .stream()
                .filter(arr -> arr[headers.get(key)].equals(arg))
                .collect(Collectors.toList());
        return tmpList;

    }

    private List<String[]> showByOption(List<String[]> tmpList, String key, String arg) {
        for (String[] array : csvCookBook.getContents()) {
            if (array[headers.get(key)].equals(arg)) {
                if (!tmpList.contains(array)) {
                    tmpList.add(array);
                }
            }
        }
        return tmpList;
    }

    private List<String[]> sortByOption(String key) {
        List<String[]> tmpList = new ArrayList<>();
        for (String[] arr : csvCookBook.getContents()) {
            String[] arrCopy = arr.clone();
            tmpList.add(arrCopy);
        }
        tmpList.sort((o1, o2) -> {
            if (o1[headers.get(key)] == null || o2[headers.get(key)] == null) {
                return 0;
            }
            return o1[headers.get(key)].compareTo(o2[headers.get(key)]);
        });
        return tmpList;
    }

    @Override
    public List<String[]> displayTodos(List<String[]> destList, String option, String argument) {
        if (option.equals("--show-incomplete")) {
//            showByOption(destList, completed, "false");
            destList = showByOption(destList, completed, "false");
        }
        if (option.equals("--show-category")) {
            destList = filter(destList, category, argument);
//            filter(destList, category, argument);
        }
        if (option.equals("--sort-by-priority")) {
            destList = sortByOption(priority);
//            sortByOption(priority);
        }
        if (option.equals("--sort-by-date")) {
//            sortByOption(due);
            destList = sortByOption(due);
        }
        return destList;
    }

    /**
     *  this logic will produce at most 3 print-out display independently.
     */
//    @Override
//    public String displayTodos(String option, String argument) {
//        List<String[]> tmpList = new ArrayList<>();
//
//        if (option.equals("--show-incomplete")) {
//            tmpList = showByOption(tmpList, completed, "false");
//
//        }
//        if (option.equals("--show-category")) {
//            tmpList = showByOption(tmpList, category, argument);
//        }
//        if (option.isEmpty()) {
//            return this.csvCookBook.toString();
//        }
//        if (option.equals("--sort-by-priority")) {
//            tmpList = sortByOption(priority);
//            CSVCookBook csvCookBookTmp = new CSVCookBook(headers, tmpList);
//            return csvCookBookTmp.toString();
//        }
//        if (option.equals("--sort-by-date")) {
//            tmpList = sortByOption(due);
//            CSVCookBook csvCookBookTmp = new CSVCookBook(headers, tmpList);
//            return csvCookBookTmp.toString();
//        }
//
//        CSVCookBook csvCookBookTmp = new CSVCookBook(headers, tmpList);
//        return csvCookBookTmp.toString();
//    }


}
