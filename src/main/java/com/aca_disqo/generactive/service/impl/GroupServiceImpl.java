package com.aca_disqo.generactive.service.impl;

import com.aca_disqo.generactive.controller.dto.GroupDTO;
import com.aca_disqo.generactive.repository.GroupRepository;
import com.aca_disqo.generactive.repository.model.Group;
import com.aca_disqo.generactive.service.GroupService;
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
        return groupRepository.save(createGroupFrom(groupDTO));
    }

    @Override
    public Group get(Long id) {
        return groupRepository.findById(id).orElseThrow(() -> new RuntimeException("No group by this id"));
    }

    @Override
    public Group update(Long id, GroupDTO groupDTO) {
        final Group group = groupRepository.findById(id).get();
//        group.setName(groupDTO.getName());
//        group.setParentGroup(groupRepository.get(groupDTO.getParentId()));
        return group;
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public Boolean deleteById(Long id) {
        groupRepository.deleteById(id);
        return groupRepository.existsById(id);
    }

    @Override
    public Group findGroupByName(String name) {
       return groupRepository.findByName(name)
               .orElseThrow(() -> new RuntimeException("Not Found group"));
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
