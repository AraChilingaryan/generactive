package com.aca_disqo.generactive.repository.model;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private int id;
    private String name;
    private Group parentGroup;
    private List<Group> subGroups = new ArrayList<>();
    private List<Item> items = new ArrayList<>();

    public Group() {
    }

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Group(int id, String name, Group parentGroup) {
        this.id = id;
        this.name = name;
        this.parentGroup = parentGroup;
    }

    public Group(int id, String name, List<Group> subGroups, List<Item> items) {
        this.id = id;
        this.name = name;
        this.subGroups = subGroups;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group getParentGroup() {
        return parentGroup;
    }

    public void setParentGroup(Group parentGroup) {
        this.parentGroup = parentGroup;
    }

    public List<Group> getSubGroups() {
        return subGroups;
    }

    public void setSubGroups(List<Group> subGroups) {
        this.subGroups = subGroups;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

//    public void print(int level) {
//        System.out.printf("GROUP - id: {%d} {%s}%n", id, name);
//        printSubGroups(++level);
//        printItems(level);
//    }
//
//    private void printSubGroups(int level) {
//        String subLevelPrefix = "  ".repeat(level);
//        for (Group group : subGroups) {
//            System.out.print(subLevelPrefix);
//            group.print(level);
//        }
//    }
//
//    private void printItems(int level) {
//        String subLevelPrefix = "  ".repeat(level);
//        for (Item item : items) {
//            System.out.print(subLevelPrefix);
//            item.print();
//        }
//    }

}

