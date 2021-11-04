package utils.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.LinkedHashMap;

/**
 * This class serves as a cookbook for CSVReader.
 */
public class CSVCookBook {
    private LinkedHashMap<String, Integer> headers;
    private List<String[]> contents;

    /**
     * Construct a new CSVCookBook class
     * @param headers  map represent the headers
     * @param contents List of String represent the contents
     */
    public CSVCookBook(LinkedHashMap<String, Integer> headers, List<String[]> contents) {
        this.headers = headers;
        this.contents = contents;
    }

    /**
     * Construct a new CSVCookBook class
     * @param headers map represent the headers
     */
    public CSVCookBook(LinkedHashMap<String, Integer> headers) {
        this.headers = headers;
        this.contents = new ArrayList<>();
    }

    /**
     * Gets the value of headers.
     *
     * @return the value of headers
     */
    public LinkedHashMap<String, Integer> getHeaders() {
        return headers;
    }

    /**
     * Gets the value of contents.
     *
     * @return the value of contents
     */
    public List<String[]> getContents() {
        return contents;
    }

    /**
     * Set/change the value of header
     * @param headers the header value we want to change
     */
    public void setHeaders(LinkedHashMap<String, Integer> headers) {
        this.headers = headers;
    }

    /**
     * Set/change the value of contents
     * @param contents the contents value we want to change
     */
    public void setContents(List<String[]> contents) {
        this.contents = contents;
    }

    /**
     * use equals method to make sure the class is reflexive, transitive, and symmetric,
     * Indicates whether some other object is "equal to" this one.
     * @param o the object itself
     * @return Boolean that indicate the Class is reflexive, transitive and symmetric.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CSVCookBook)) return false;
        CSVCookBook that = (CSVCookBook) o;
        return Objects.equals(getHeaders(), that.getHeaders()) && Objects.equals(getContents(), that.getContents());
    }

    /**
     *
     * @return a hash code value for the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(getHeaders(), getContents());
    }

    /**
     * make the class and class field more readable by create toString method,
     * @return a string that represent the class, and class field
     */
    @Override
    public String toString() {
        String quote = "\"", comma = ",";
        StringBuilder sb = new StringBuilder();
        for (String head : headers.keySet()) {
            sb.append(quote).append(head).append(quote).append(comma);
        }
        sb.deleteCharAt(sb.length() - 1).append("\n");
        for (String[] array : contents) {
            for (String str : array) {
                if(str.equals("")) str = "?";
                sb.append(quote).append(str).append(quote).append(comma);

            }
            sb.deleteCharAt(sb.length() - 1).append("\n");
        }
        return sb.toString();
    }
}
