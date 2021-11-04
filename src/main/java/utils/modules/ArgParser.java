package utils.modules;

import utils.exception.IllegalArgComboException;

import java.util.*;

public class ArgParser {
    private static final String csvFile = "--csv-file";
    private static final String todoText = "--todo-text";
    private static final String sortByDate = "--sort-by-date";
    private static final String sortByPriority = "--sort-by-priority";
    private static final String addTodo = "--add-todo";
    private static final String completeTodo = "--complete-todo";

    private static ArrayList<String> commandWithArgs = new ArrayList<String>(){{
        add("--csv-file");
        add("--todo-text");
        add("--due");
        add("--priority");
        add("--category");
        add("--show-category");}};


    private HashMap<String, String> arguments;

    public ArgParser(String[] args) {
        this.arguments = parse(args);
    }

    public HashMap<String, String> getArguments() {
        return this.arguments;
    }

    public void checkValidity(String[] args){
        if (Arrays.stream(args).noneMatch(csvFile::equals)) {
            throw new IllegalArgComboException(csvFile + " is required but not provided");
        }
        if (Arrays.asList(args).contains(sortByDate) && Arrays.asList(args).contains(sortByPriority)) {
            throw new IllegalArgComboException("Two sort options can not be combined.");
        }
        if (Arrays.asList(args).contains(addTodo)) {
            if (Arrays.stream(args).noneMatch(todoText::equals)) {
                throw new IllegalArgComboException("If the option \"--add-todo\" is provided, "
                        + "then --todo-text must also be provided.");
            }
        }
    }


    public HashMap<String, String> parse(String[] args) {
        checkValidity(args);
        HashMap<String, String> res = new HashMap<>();
        List<String> toCompleteId = new ArrayList<>();
        for(int i = 0; i < args.length; i++) {
            if (args[i].equals(completeTodo)) {
                toCompleteId.add(args[i + 1]);
            }
            if (commandWithArgs.contains(args[i])) {
                res.put(args[i], args[i + 1]);
            } else {
               res.put(args[i], "true");
            }
        }
        if (toCompleteId.size() > 0) {
            res.put(completeTodo, listToString(toCompleteId));
        }
        return res;
    }

    private static String listToString(List<String> lst) {
        StringBuilder sb = new StringBuilder();
        for (String str : lst) {
            sb.append("\"").append(str).append("\"").append(",");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

}
