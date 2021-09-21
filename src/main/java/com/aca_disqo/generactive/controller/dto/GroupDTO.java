package com.aca_disqo.generactive.controller.dto;

import com.aca_disqo.generactive.utils.Resolution;

import java.util.List;

public class GroupDTO {

    private Long id;
    private Long parentId;
    private String name;
    private List<Long> subGroups;
    private List<Long> items;
    private Resolution resolution;


    public GroupDTO() {
    }

    public GroupDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public GroupDTO(Long id, Long parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getSubGroups() {
        return subGroups;
    }

    public void setSubGroups(List<Long> subGroups) {
        this.subGroups = subGroups;
    }

    public List<Long> getItems() {
        return items;
    }

    public void setItems(List<Long> items) {
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
