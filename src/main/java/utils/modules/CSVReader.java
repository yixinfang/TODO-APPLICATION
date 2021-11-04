package utils.modules;

import utils.pojo.CSVCookBook;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * This class represents the CSV file reader. The CSV file is a plain text file, containing data
 * organized into columns separated by comma. The data in each column is enclosed in double quotes.
 * The first line of the file contains the header for each column, while each of the remaining rows
 * presents the information for each individual supporters. The main task of CSVReader is to convert
 * a csv file into a CSVCookBook object, which will be used in the FileGenerator.
 */
public class CSVReader {
    private static final String splitter = "\",\"";
    private static final String letterToRemove = "\"";
    private static final String letterToReplace = "";
    protected static final Integer ZERO = 0;
    protected static final Integer ONE = 1;

    /**
     * This method takes the file path of a target CSV file, reads and generate a CSVCookBook.
     *
     * @param filePath - the file path of a target CSV file.
     * @return a CSVCookBook object.
     */
    public static CSVCookBook readContent(String filePath) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        LinkedHashMap<String, Integer> headers = readHeaders(reader);
        List<String[]> contents = new ArrayList<>();
        contentReaderHelper(contents, reader);
        return new CSVCookBook(headers, contents);
    }

    /**
     * This helper method serves to read the information temporarily stored in the BufferReader and
     * update the header in the CSVCookBook.
     *
     * @param reader - the BufferReader used to store the information read from the CSV file.
     * @return the header of the CSVCookBook.
     */
    private static LinkedHashMap<String, Integer> readHeaders(BufferedReader reader) {
        String tmp = null;
        try {
            tmp = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] headers = doubleQuoteHandler(tmp);
        LinkedHashMap<String, Integer> res = new LinkedHashMap<>();
        for (int i = ZERO; i < headers.length; i++) {
            res.put(headers[i], i);
        }
        return res;
    }

    /**
     * This helper method serves to read the information temporarily stored in the BufferReader and
     * update the contents in the CSVCookBook, also helps to close the file if necessary.
     *
     * @param contents - the contents in the CSVCookBook which is to be updated.
     * @param reader - the BufferReader used to store the information read from the CSV file.
     */
    private static void contentReaderHelper(List<String[]> contents, BufferedReader reader) {
        String tmp;
        try {
            while ((tmp = reader.readLine()) != null) {
                contents.add(doubleQuoteHandler(tmp));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * This helper method serves to convert the String format of each line into array of Strings, by
     * replacing target letter and splitting a integrate String into several entries.
     *
     * @param tmp - the String line read from the reader.
     * @return - An array of Strings which is made up by the entries in one line.
     */
    private static String[] doubleQuoteHandler(String tmp) {
        String[] curr = tmp.split(splitter);
        curr[ZERO] = curr[ZERO].replace(letterToRemove, letterToReplace);
        curr[curr.length - ONE] = curr[curr.length - ONE].replace(letterToRemove, letterToReplace);
        return curr;
    }
}
