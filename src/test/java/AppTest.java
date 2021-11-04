import org.junit.Test;
import utils.exception.IllegalArgComboException;

import java.io.FileNotFoundException;

import static org.junit.Assert.fail;

public class AppTest {
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


//    @Before
//    public void setUp() throws Exception {
//    }

    @Test
    public void testAddNewTodo() {
        String[] args1 = {csvFile, "todo3", addTodo, todoText, "makeup", due, "07/29/2021", priority, "2", category, "lalala"};
        String[] args2 = {csvFile, "todo4", addTodo, todoText, "coding", priority, "1"};
        String[] args3 = {csvFile, "todo3", addTodo, todoText, "running", priority, "2", category, "lalala"};
        try {
            App.main(args1);
            App.main(args2);
            App.main(args3);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddNewTodoAndCompleteToDo() {
        String[] args1 = {csvFile, "todo3", addTodo, todoText, "makeup", due, "07/29/2021", priority, "2", category, "lalala"};
        String[] args2 = {csvFile, "todo4", addTodo, todoText, "coding", completeTodo, "1", priority, "1"};
        String[] args3 = {csvFile, "todo3", addTodo, todoText, "running", priority, "2", category, "lalala"};
        try {
            App.main(args1);
            App.main(args2);
            App.main(args3);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        String[] args1 = {csvFile, "todo3", addTodo, todoText, "makeup", due, "07/29/2021", priority, "2", category, "lalala"};

        try {
            App.main(args1);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddNewTodoAndCompleteToDoInOneCommand() {
        //String[] args1 = {csvFile, "todo3", addTodo, todoText, "makeup", due, "07/29/2021", priority, "2", category, "lalala"};
        String[] args2 = {csvFile, "todo4", addTodo, todoText, "coding", completeTodo, "1", priority, "1"};
        String[] args3 = {csvFile, "todo3", addTodo, todoText, "running", completeTodo, "1", completeTodo, "2", priority, "2", category, "lalala"};
        try {
            //    App.main(args1);
            App.main(args2);
            App.main(args3);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddNewTodoAndCompleteToDoInOneCommand2() {

        String[] args3 = {csvFile, "todo3", completeTodo, "3", priority, "2", category, "lalala"};
        try {

            App.main(args3);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testApp() {
        String[] args1 = {csvFile, "todo", addTodo, todoText, "makeup", due, "07/29/2021", priority, "2", category, "lalala", display};
        String[] args2 = {csvFile, "todo", addTodo, todoText, "coding", completeTodo, "1", priority, "1", display};
        String[] args3 = {csvFile, "todo", addTodo, todoText, "running", priority, "2", category, "lalala", display, showCategory, "lalala", sortByPriority};
        try {
            App.main(args1);
            App.main(args2);
            App.main(args3);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAppGenerateTwoCSVFile() {
        String[] args1 = {csvFile, "todo1", addTodo, todoText, "makeup", due, "07/29/2021", priority, "2", category,
                "lalala", display};
        String[] args2 = {csvFile, "todo2", addTodo, todoText, "coding", completeTodo, "1", priority, "1", display};
        String[] args3 = {csvFile, "todo1", addTodo, todoText, "running", completeTodo, "1", completeTodo, "2", priority, "1", category,
                "lala", display, showCategory, "lalala"};
        String[] args4 = {csvFile, "todo1", addTodo, todoText, "road trip", priority, "3", category,
                "bos-sv", display, sortByPriority};
        String[] args5 = {csvFile, "todo1", todoText, "road trip", due, "", priority, "3", category,
                "bos-sv", display, showCategory, "bos-sv"};
        String[] args6 = {csvFile, "todo1", addTodo, todoText, "makeup", due, "07/30/2021", priority, "1", category,
                "lalala", display, showIncomplete, sortByDate};
        String[] args7 = {category, "lalala", csvFile, "todo1", addTodo, todoText, "may it be", due, "08/02/2021",
                priority, "2", display, showCategory, "lalala", showIncomplete, sortByDate};
        try {
            App.main(args1);
            App.main(args2);
            App.main(args3);
            App.main(args4);
            App.main(args5);
            App.main(args6);
            App.main(args7);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDisplayAllAndSortByDue() {
        String[] args8 = {csvFile, "todo1", display, sortByDate};
        try {
            App.main(args8);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgComboException ignored) {
        }

    }


    @Test
    public void testMissingCSVFile() {
        String[] args6 = {"todo1", addTodo, todoText, "makeup", due, "07/30/2021", priority, "1", category,
                "lalala", display, sortByDate};
        try {
            App.main(args6);
        } catch (IllegalArgComboException | FileNotFoundException e) {
            System.out.println("--csv-file is required but not provided");
        }
    }

    @Test
    public void testTwoSort() {
        String[] args6 = {csvFile, "todo1", addTodo, todoText, "makeup", due, "07/30/2021", priority, "1", category,
                "lalala", display, sortByPriority, sortByDate};
        try {
            App.main(args6);
        } catch (IllegalArgComboException | FileNotFoundException e) {
            System.out.println("Two sort options can not be combined.");
        }
    }

    @Test
    public void testMissingToDoText() {
        String[] args6 = {csvFile, "todo1", addTodo, "makeup", due, "07/30/2021", priority, "1", category,
                "lalala", display, sortByPriority};
        try {
            App.main(args6);
        } catch (IllegalArgComboException | FileNotFoundException e) {
            System.out.println("If the option \"--add-todo\" is provided, then --todo-text must also be provided.");
        }
    }

    @Test
    public void testIncorrectArgForCategory() {
        String[] args6 = {csvFile, "todo1", addTodo, todoText, "makeup", due, "07/30/2021", priority, "1", category,
                "--lalala", display, showIncomplete, sortByDate};
        try {
            App.main(args6);
        } catch (IllegalArgComboException | FileNotFoundException e) {
            System.out.println("The value of category can not be started with \"--\"");
        }
    }

    @Test
    public void testEmptyDueGiven1() {
        String[] args1 = {csvFile, "todo1", addTodo, todoText, "makeup", due, priority, "2", category,
                "lalala", display};
        try {
            App.main(args1);
            fail();
        } catch (FileNotFoundException e) {
        } catch (IllegalArgComboException e) {

        }
    }

    @Test
    public void testEmptyDueGiven2() {
        String[] args1 = {csvFile, "todo1", addTodo, todoText, "makeup", due, "", priority, "2", category,
                "lalala", display};
        try {
            App.main(args1);
            fail();
        } catch (FileNotFoundException e) {
        } catch (IllegalArgComboException e) {

        }
    }


    @Test
    public void testIncorrectDueFormat() {
        String[] args6 = {csvFile, "todo1", addTodo, todoText, "makeup", due, "07-30-2021", priority, "1", category,
                "lalala", display, showIncomplete, sortByDate};
        try {
            App.main(args6);
        } catch (IllegalArgComboException | FileNotFoundException e) {
            System.out.println("Please provide the date as the format MM/dd/yyyy.");
        }
    }

    @Test
    public void setCompletedFileNotFound() {
        String[] args3 = {csvFile, "todo8", completeTodo, "1", completeTodo, "2", priority, "1", category,
                "lala", display, showCategory, "lalala"};
        try {
            App.main(args3);
            fail();
        } catch (FileNotFoundException e) {
        } catch (IllegalArgComboException e) {

        }
    }

    @Test
    public void setInvalidPriority() {
        String[] args3 = {csvFile, "todo2", addTodo, todoText, "makeup", priority, "100", category,
                "lala", display, showCategory, "lalala"};
        try {
            App.main(args3);
        } catch (FileNotFoundException | IllegalArgComboException e) {
            System.out.println("If choosing to specify a priority, it must be 1, 2, or 3, "
                    + "with 1 being the highest priority.\n" +
                    "If no priority is specified, the todo can be treated as lowest priority (i.e., 3)");
        }
    }

    @Test
    public void setCompletedInvalidId() {
        String[] args3 = {csvFile, "todo2", addTodo, todoText, "makeup", completeTodo, "100", category,
                "lala", display, showCategory, "lalala"};
        try {
            App.main(args3);
        } catch (FileNotFoundException | IllegalArgComboException e) {
            System.out.println("Invalid Id is given");
        }
    }

    /** If want to clear the generated CSV files, un-comment this method as well as the whole class
     * in the utils/DeleteUtil.
     */
//    @After
//    public void clear() {
//        File temp = new File(App.root);
//        DeleteUtil.delete(temp);
//    }
}