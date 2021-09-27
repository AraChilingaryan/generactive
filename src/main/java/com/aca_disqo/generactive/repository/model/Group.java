package com.aca_disqo.generactive.repository.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"group\"")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_id_seq")
    @SequenceGenerator(name = "group_id_seq", sequenceName = "group_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private List<Item> items = new ArrayList<>();

    @ManyToOne
    private Group parentGroup;

    @OneToMany(mappedBy = "parentGroup", fetch = FetchType.EAGER)
    private final List<Group> subGroups = new ArrayList<>();

    public Group() {
    }

    public Group(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setParentGroup(Group parentGroup) {
        this.parentGroup = parentGroup;
    }

    public List<Group> getSubGroups() {
        return subGroups;
    }

    public void addSubGroup(Group group) {
        this.subGroups.add(group);
        group.setParentGroup(this);
    }

    public void addItem(Item item) {
        this.items.add(item);
        item.setGroup(this);
    }

    public void addItems(List<Item> items) {
        for (Item item : items) {
            addItem(item);
        }
    }
}
