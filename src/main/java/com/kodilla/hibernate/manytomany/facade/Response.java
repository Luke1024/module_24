package com.kodilla.hibernate.manytomany.facade;

import java.util.ArrayList;
import java.util.List;

public class Response {
    private List entities = new ArrayList<>();
    private String query;
    private Command command;

    public <T> List<T> getEntities() {
        return entities;
    }

    public String getQuery() {
        return query;
    }

    public <T> void setEntities(List<T> entities) {
        this.entities = entities;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}