package model;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ModelTest {

    @Test
    public void setAddTodo() {
    }

    @Test
    public void setCompleted() {
    }

    @Test
    public void setDisplay() {
    }

    @Test
    public void getCsvCookBook() {
    }
    @Test
    public void testIsValidDueGiven() {
        String dateString = "07/29/2021";
        try {
            Method isValidDueGiven =
                    Model.class.getDeclaredMethod("isValidDueGiven", String.class);
            isValidDueGiven.setAccessible(true);
            try {
                Boolean flag = (Boolean) isValidDueGiven.invoke(Model.class, dateString);
                assertTrue(flag);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIsValidDueGiven2() {
        String dateString = "07/39/2021";
        try {
            Method isValidDueGiven =
                    Model.class.getDeclaredMethod("isValidDueGiven", String.class);
            isValidDueGiven.setAccessible(true);
            try {
                Boolean flag = (Boolean) isValidDueGiven.invoke(Model.class, dateString);
                assertFalse(flag);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIsValidDueGiven3() {
        String dateString = "07-29-21";
        try {
            Method isValidDueGiven =
                    Model.class.getDeclaredMethod("isValidDueGiven", String.class);
            isValidDueGiven.setAccessible(true);
            try {
                Boolean flag = (Boolean) isValidDueGiven.invoke(Model.class, dateString);
                assertFalse(flag);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}