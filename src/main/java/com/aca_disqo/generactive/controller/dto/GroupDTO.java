package com.aca_disqo.generactive.controller.dto;

import com.aca_disqo.generactive.utils.Resolution;

import java.util.List;

public class GroupDTO {

    private int id;
    private int parentId;
    private String name;
    private List<Integer> subGroups;
    private List<Integer> items;
    private Resolution resolution;


    public GroupDTO() {
    }

    public GroupDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public GroupDTO(int id, int parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getSubGroups() {
        return subGroups;
    }

    public void setSubGroups(List<Integer> subGroups) {
        this.subGroups = subGroups;
    }

    public List<Integer> getItems() {
        return items;
    }

    public void setItems(List<Integer> items) {
        this.items = items;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    @Override
    public String toString() {
        return "GroupDTO{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", subGroups=" +subGroups +
                ", items=" + items +
                '}';
    }
}
