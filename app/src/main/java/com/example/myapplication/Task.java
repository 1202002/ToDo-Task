package com.example.myapplication;

public class Task {

    private String title ;
    private String description ;
    private Boolean done ;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.done = false ;
    }

//     The Getter of the Class
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getDone() {
        return done;
    }
    //    The Setter of the class

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}

