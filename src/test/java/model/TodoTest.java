package model;

import static org.junit.Assert.*;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import org.junit.Before;
import org.junit.Test;

public class TodoTest {
    private Todo todo1, todo2, todo3, todo4, todo5;
    private Date date1, date2, date3, date4;

    @Before
    public void setUp() throws Exception {
        date1 = new Date(2011);
        date2 = new Date(2022);
        date3 = new Date(2033);


        todo1 = new Todo("todo1","false",date1,1,"school");
        todo2 = new Todo("todo2","false",date2,1,"school");
        todo3 = new Todo("todo3","false",date3,1,"school");
    }





    @Test
    public void compareTo() {
        assertEquals(todo1.compareTo(todo2), -1);
    }

    @Test
    public void testToString(){
        String expected = "Todo{" +
                "text='" + "todo1" + '\'' +
                ", completed='" + "false" + '\'' +
                ", due=" + date1 +
                ", priority=" + 1 +
                ", category='" + "school"+ '\'' +
                '}';
        assertEquals(expected, todo1.toString());
    }

    @Test
    public void testHashCode(){
        int hash = Objects.hash("todo1", "false", date1,1, "school");
        assertEquals(hash, todo1.hashCode());
    }

    @Test
    public void equalsReflexive(){
        assertTrue(todo1.equals(todo1));
    }

    @Test
    public void equalReflexive2() {
        assertFalse(todo1.equals(todo2));
    }

    @Test
    public void equalsDifferentDataTypes(){
        assertFalse(todo1.equals(date1));
    }

    @Test
    public void equalsSameFields() {
        todo2 = new Todo("todo1","false",date1,1,"school");
        assertTrue(todo1.equals(todo2));
    }

    @Test
    public void equalsNotSameToDoText(){
        todo2 = new Todo("todo2","false",date1,1,"school");
        assertFalse(todo1.equals(todo2));
    }

    @Test
    public void equalsNotSameComplete(){
        todo2 = new Todo("todo1","true",date1,1,"school");
        assertFalse(todo1.equals(todo2));
    }

    @Test
    public void equalsNotSameDate() {
        todo2 = new Todo("todo1","false",date2,1,"school");
        assertFalse(todo1.equals(todo2));
    }

    @Test
    public void equalsSamePriority() {
        todo2 = new Todo("todo1","false",date1,2,"school");
        assertFalse(todo1.equals(todo2));
    }

    @Test
    public void equalsSameCate() {
        todo2 = new Todo("todo1","false",date1,1,"gchool");
        assertFalse(todo1.equals(todo2));
    }

}