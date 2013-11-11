package com.natercio.myapplication;

/**
 * Created by natercio on 11/10/13.
 */
public class ToDoTask {

    long id;

    String title;

    String content;

    String dueDate = null;

    public ToDoTask(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public ToDoTask(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
