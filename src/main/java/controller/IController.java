package controller;

import java.io.FileNotFoundException;

public interface IController {
    void callAddTodo() throws FileNotFoundException;
    void callComplete() throws FileNotFoundException;
    void callDisplay() throws FileNotFoundException;
}
