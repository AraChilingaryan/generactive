package com.aca_disqo.generactive.container;

import com.aca_disqo.generactive.repository.model.Group;
import com.aca_disqo.generactive.repository.model.Item;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private static Database database = null;
    private final List<Group> groups = new ArrayList<>();
    private final List<Item> items = new ArrayList<>();

    private Database() {
    }

    public static Database getInstance(){
        if(database == null){
            database = new Database();
        }
        return database;
    }

    public List<Group> getGroupList() {
        return groups;
    }

    public List<Item> getItems() {
        return items;
    }
}
