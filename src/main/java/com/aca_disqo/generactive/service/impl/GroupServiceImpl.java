package com.aca_disqo.generactive.service.impl;

import com.aca_disqo.generactive.context.ApplicationContext;
import com.aca_disqo.generactive.controller.dto.GroupDTO;
import com.aca_disqo.generactive.repository.GroupRepository;
import com.aca_disqo.generactive.repository.model.Group;
import com.aca_disqo.generactive.service.GroupService;

import java.util.List;

public class GroupServiceImpl implements GroupService {

    private static GroupService groupService = null;
    private final GroupRepository groupRepository;

    private GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public static GroupService getInstance() {
        if (groupService == null) {
            groupService = new GroupServiceImpl(ApplicationContext.getInstance().getGroupRepository());
        }
        return groupService;
    }

    @Override
    public Group create(GroupDTO groupDTO) {
        return groupRepository.create(createGroupFrom(groupDTO)).get();
    }

    @Override
    public Group get(Long id) {
        return groupRepository.get(id).orElseThrow(() -> new RuntimeException("No group by this id"));
    }

    @Override
    public Group update(Long id, GroupDTO groupDTO) {
        final Group group = groupRepository.get(id).get();
//        group.setName(groupDTO.getName());
//        group.setParentGroup(groupRepository.get(groupDTO.getParentId()));
        return group;
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.getAll();
    }

    @Override
    public void deleteById(Long id) {
        groupRepository.deleteById(id);
    }

    @Override
    public Group findGroupByName(String name) {
        //return groupRepository.findGroupByName(name);
        return null;
    }

    private Group createGroupFrom(GroupDTO groupDTO) {
        final Group group = new Group();
        group.setName(groupDTO.getName());
        if (groupDTO.getParentId() != null) {
            Group parentGroup = get(groupDTO.getParentId());
            parentGroup.getSubGroups().add(group);
            group.setParentGroup(parentGroup);
        }
        return group;
    }
}
