package com.sha.todomongo.domain;

public class ToDoBuilder {

    private static com.sha.todomongo.domain.ToDoBuilder instance = new com.sha.todomongo.domain.ToDoBuilder();
    private String id = null;
    private String description ="";

    private ToDoBuilder(){}

    public static com.sha.todomongo.domain.ToDoBuilder create(){
        return instance;
    }

    public com.sha.todomongo.domain.ToDoBuilder withDescription(String description){
        this.description = description;
        return instance;
    }

    public com.sha.todomongo.domain.ToDoBuilder withId(String id){
        this.id = id;
        return instance;
    }

    public  ToDo build() {
       ToDo result = new ToDo(this.description);
       if(id !=null){
           result.setId(id);
       }
       return result;


    }
}
