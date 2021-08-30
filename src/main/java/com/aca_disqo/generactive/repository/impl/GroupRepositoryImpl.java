package com.aca_disqo.generactive.repository.impl;

import com.aca_disqo.generactive.container.Database;
import com.aca_disqo.generactive.context.ApplicationContext;
import com.aca_disqo.generactive.repository.GroupRepository;
import com.aca_disqo.generactive.repository.model.Group;

import java.util.List;

public class GroupRepositoryImpl implements GroupRepository {

    private static GroupRepository groupRepository = null;
    private final Database database;

    public GroupRepositoryImpl(Database database) {
        this.database = database;
    }

    public static GroupRepository getInstance() {
        if (groupRepository == null) {
            groupRepository = new GroupRepositoryImpl(ApplicationContext.getInstance().getDatabase());
        }
        return groupRepository;
    }

    @Override
    public Group create(Group group) {
        database.getGroupList().add(group);
        return  database.getGroupList().get(database.getGroupList().size() - 1);
    }

    @Override
    public Group get(int id) {
        return database.getGroupList().stream().
                filter(group -> group.getId() == id).findAny().get();
    }

    @Override
    public List<Group> getAll() {
        return database.getGroupList();
    }

    @Override
    public void deleteById(int id) {
        database.getGroupList().remove(get(id));
    }

    @Override
    public Group findGroupByName(String name) {
        return database.getGroupList().stream()
                .filter(group -> group.getName().equals(name))
                .findAny().get();
    }
}
