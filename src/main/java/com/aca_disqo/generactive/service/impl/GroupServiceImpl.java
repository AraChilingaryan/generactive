package com.aca_disqo.generactive.service.impl;

import com.aca_disqo.generactive.controller.dto.GroupDTO;
import com.aca_disqo.generactive.repository.GroupRepository;
import com.aca_disqo.generactive.repository.model.Group;
import com.aca_disqo.generactive.service.GroupService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
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
            parentGroup.addSubGroup(group);
            group.setParentGroup(parentGroup);
        }
        return group;
    }
}
