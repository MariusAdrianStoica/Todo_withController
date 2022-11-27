package se.lexicon.dao.impl;

import se.lexicon.dao.TodoItemDao;
import se.lexicon.dao.impl.sequencer.TodoItemIdSequencer;
import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;

import java.util.ArrayList;
import java.util.List;

public class TodoItemDaoImpl implements TodoItemDao {

    private List<TodoItem> storage;

    /** -> step2 Singleton design pattern - private static field - instance -  with the type of the class  */
    private static TodoItemDaoImpl instance;

    /** -> step1 Singleton design pattern - constructor private */

    private TodoItemDaoImpl(){
        storage= new ArrayList<>();
    }

    /** -> step3 Singleton design pattern //public static method - getInstance() -  with the type of the class */
    public static TodoItemDaoImpl getInstance(){
        if (instance == null) instance = new TodoItemDaoImpl(); //instantiate
        return instance;
    }

    @Override
    public TodoItem create(TodoItem model) {
        if (model == null) throw new IllegalArgumentException("TodoItem was null");
        model.setId(TodoItemIdSequencer.nextId()); //generate id only when register new TodoItem to storage
        storage.add(model);
        return model;
    }

    @Override
    public TodoItem findById(Integer id) {
        if (id== null) throw new IllegalArgumentException("id was null");
        for (TodoItem todoitem : storage)
            if(todoitem.getId().equals(id)) return todoitem;
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        // todo: implement later
        return false;
    }

    @Override
    public void update(TodoItem model) {
        // todo: implement later

    }

    @Override
    public List<TodoItem> findAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public List<TodoItem> findAllAvailable() {
        // todo: implement later
        return null;
    }

    @Override
    public List<TodoItem> findAllExpiredAndInCompleted() {
        // todo: implement later
        return null;
    }
}
