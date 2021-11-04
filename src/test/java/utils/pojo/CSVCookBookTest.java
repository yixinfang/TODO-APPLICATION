package utils.pojo;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

public class CSVCookBookTest {
    private CSVCookBook csvCookBook;
    private LinkedHashMap<String, Integer> headers, headers2;
    private List<String[]> contents, contents2;

    @Before
    public void setUp() {
        headers = new LinkedHashMap<>();
        headers.put("Fruit", 0);
        headers.put("Make-up", 1);
        headers.put("Sport", 2);
        String[] array1 = new String[]{"Apple", "Eyeshadow", "Running"};
        String[] array2 = new String[]{"Banana", "Foundation", "Swimming"};
        contents = new ArrayList<>(Arrays.asList(array1, array2));
        csvCookBook = new CSVCookBook(headers, contents);
    }


    @Test
    public void getHeaders() {
        assertEquals(headers, csvCookBook.getHeaders());
    }

    @Test
    public void getContents() {
        assertEquals(contents,csvCookBook.getContents());
    }

    @Test
    public void setHeaders(){
        headers2 = new LinkedHashMap<>();
        headers2.put("ABC", 0);
        headers2.put("EFG", 1);
        headers2.put("HJI", 2);
        csvCookBook.setHeaders(headers2);
        assertEquals(headers2, csvCookBook.getHeaders());
    }

    @Test
    public void setContents(){
        String[] array1 = new String[]{"dasd", "Evzxcv", "zxc"};
        String[] array2 = new String[]{"Baasd", "Fouasd", "Sasming"};
        contents2 = new ArrayList<>(Arrays.asList(array1, array2));
        csvCookBook.setContents(contents2);
        assertEquals(contents2, csvCookBook.getContents());
    }



    @Test
    public void testEquals() {
        CSVCookBook csvCookBook1 = new CSVCookBook(headers, contents);
        assertTrue(csvCookBook.equals(csvCookBook));
        assertTrue(csvCookBook1.equals(csvCookBook));
        assertFalse(csvCookBook.equals(headers));
    }

    @Test
    public void testNotEquals1() {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        map.put("Fruit", 0);
        map.put("Make-up", 1);
        map.put("Sport", 3);
        CSVCookBook csvCookBook1 = new CSVCookBook(map, contents);
        assertFalse(csvCookBook1.equals(csvCookBook));
    }
    @Test
    public void testNotEquals2() {
        String[] array1 = new String[]{"Apple", "Eyeshadow", "Running"};
        String[] array2 = new String[]{"Banana", "Foundation", "Swimming"};
        String[] array3 = new String[]{"Pear", "LipStick", "Hiking"};
        List<String[]> contents1 = new ArrayList<>(Arrays.asList(array1, array2, array3));
        CSVCookBook csvCookBook1 = new CSVCookBook(headers, contents1);
        assertFalse(csvCookBook1.equals(csvCookBook));

    }

    @Test
    public void testHashCode() {
        int expected = csvCookBook.hashCode();
        CSVCookBook csvCookBook1 = new CSVCookBook(headers, contents);
        assertEquals(expected,csvCookBook1.hashCode());

    }

    @Test
    public void testToString() {
        String expected ="\"Fruit\",\"Make-up\",\"Sport\"\n"
                + "\"Apple\",\"Eyeshadow\",\"Running\"\n"
                + "\"Banana\",\"Foundation\",\"Swimming\"\n";

        //System.out.println(csvCookBook.toString());
        assertEquals(expected, csvCookBook.toString());
    }
}
