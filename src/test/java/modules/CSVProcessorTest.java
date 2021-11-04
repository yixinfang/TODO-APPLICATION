package modules;

import org.junit.Test;
import utils.modules.CSVProcessor;
import utils.pojo.CSVCookBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CSVProcessorTest {

    @Test
    public void csvWriter() {
        String[] data = new String[]{"1", "Finish HW9", "false", "8/2/2021", "1", "school"};
        String[] data2 = new String[]{"2", "Finish HW9", "true", "8/2/2021", "1", "school"};
        try {
            CSVProcessor.csvAppender(1, data, "1.csv");
            CSVProcessor.csvAppender(2, data2, "1.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void csvPrinter() {
        LinkedHashMap<String, Integer> headers = new LinkedHashMap<>();
        headers.put("Fruit", 0);
        headers.put("Make-up", 1);
        headers.put("Sport", 2);
        String[] array1 = new String[]{"Apple", "Eyeshadow", "Running"};
        String[] array2 = new String[]{"Banana", "Foundation", "Swimming7"};
        List<String[]> contents = new ArrayList<>(Arrays.asList(array1, array2));
        CSVCookBook csvCookBook1 = new CSVCookBook(headers, contents);
        CSVProcessor.printCSVCookBook(csvCookBook1, "todo5.csv");
    }

    @Test
    public void csvPrinter2() {
        LinkedHashMap<String, Integer> headers = new LinkedHashMap<>();
        headers.put("Fruit", 0);
        headers.put("Make-up", 1);
        headers.put("Sport", 2);
        String[] array1 = new String[]{"Apple", "Eyeshadow", "Running"};
        String[] array2 = new String[]{"Banana", "Foundation", "Swimming6"};
        List<String[]> contents = new ArrayList<>(Arrays.asList(array1, array2));
        CSVCookBook csvCookBook1 = new CSVCookBook(headers, contents);
        CSVProcessor.printCSVCookBook(csvCookBook1, "todo1.csv");
    }


    @Test
    public void arrayToString() {
        List<String> lst = new ArrayList<>();
        lst.add("1");
        lst.add("2");
        StringBuilder sb = new StringBuilder();
        for (String str : lst) {
            sb.append("\"").append(str).append("\"").append(",");
        }
        String lstTmp = sb.deleteCharAt(sb.length() - 1).toString();
        String splitter = "\",\"";
        String doubleQuote = "\"";
        String[] tmp = lstTmp.split(splitter);
        tmp[0] = tmp[0].replace(doubleQuote, "");
        tmp[tmp.length - 1] = tmp[tmp.length - 1].replace(doubleQuote, "");
        String expected = "[1, 2]";
        assertEquals(expected, Arrays.toString(tmp));
    }

    @Test
    public void csvCookBookToString() {
        LinkedHashMap<String, Integer> headers = new LinkedHashMap<>();
        headers.put("Fruit", 0);
        headers.put("Make-up", 1);
        headers.put("Sport", 2);
        String[] array1 = new String[]{"Apple", "Eyeshadow", "Running"};
        String[] array2 = new String[]{"Banana", "Foundation", "Swimming"};
        List<String[]> contents = new ArrayList<>(Arrays.asList(array1, array2));
        CSVCookBook csvCookBook1 = new CSVCookBook(headers, contents);
        String expected = "\"Fruit\",\"Make-up\",\"Sport\"\n"
                + "\"Apple\",\"Eyeshadow\",\"Running\"\n"
                + "\"Banana\",\"Foundation\",\"Swimming\"\n";
        assertEquals(expected, csvCookBook1.toString());
    }
}