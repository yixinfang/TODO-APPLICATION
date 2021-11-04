package utils.modules;

import utils.pojo.CSVCookBook;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * CSVProcessor is a class that allow us to manipulate the csv file, such as append a new line on the CSV file, print
 * the csvCookBook as a CSV file.
 */
public class CSVProcessor {
    protected static final String[] headers = new String[]{"id", "text", "completed", "due", "priority", "category"};
    protected static final String root = "generatedCSVs";

    /**
     * This method is used to add a new todo for each request from the user. Only one new todo every time.
     * Logic: if the directory is not exist, create the directory first.
     *        if (id == 1) (a new csv should be created), write the headers and the content for this todo.
     *        else, append this todo on an existing csv.
     * Provide two method to print.
     * @param id - the specified id.
     * @param data - the data for a new todo.
     * @param filePath - the file path.
     * @throws IOException ioexception.
     */
    public static void csvAppender(int id, String[] data, String filePath) throws IOException {
        String fileName = root + File.separatorChar + filePath + ".csv";
        File dir = new File(root);
        if (!dir.exists()) dir.mkdirs();
        if (id == 1) Files.write(Paths.get(fileName), arrayToString(headers).getBytes(StandardCharsets.UTF_8));
        Files.write(Paths.get(fileName), arrayToString(data).getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.APPEND);
    }

    /**
     * Method to print out the data within the CSVCookBook into a CSV file.
     * @param csvCookBook - the given CSVCookBook.
     * @param filePath - the file path.
     */
    public static void printCSVCookBook(CSVCookBook csvCookBook, String filePath) {
        List<String[]> contents = csvCookBook.getContents();
        try {
            File tmp = File.createTempFile("tmp", "");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tmp));
            bufferedWriter.write(arrayToString(headers));
            for (String[] content : contents) {
                bufferedWriter.write(arrayToString(content));
            }
            bufferedWriter.close();
            File oldFile = new File(root + File.separatorChar + filePath +".csv");
            if (oldFile.delete()) {
                tmp.renameTo(oldFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Help method to convert a string array into a string.
     * @param data - a string array
     * @return - a string.
     */
    private static String arrayToString(String[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : data) {
            stringBuilder.append("\"").append(str).append("\"").append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1).append("\n");
        return stringBuilder.toString();
    }

}
