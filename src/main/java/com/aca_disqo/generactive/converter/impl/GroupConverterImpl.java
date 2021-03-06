package com.aca_disqo.generactive.converter.impl;

import com.aca_disqo.generactive.controller.dto.GroupDTO;
import com.aca_disqo.generactive.converter.GroupConverter;
import com.aca_disqo.generactive.converter.ItemConverter;
import com.aca_disqo.generactive.repository.model.Group;

import java.util.List;
import java.util.stream.Collectors;

public class GroupConverterImpl implements GroupConverter {

    private final ItemConverter itemConverter;
    private static GroupConverter groupConverter;

    public GroupConverterImpl(ItemConverter itemConverter) {
        this.itemConverter = itemConverter;
    }

    public static GroupConverter getInstance() {
        if (groupConverter == null) {
            groupConverter = new GroupConverterImpl(ItemConverterImpl.getInstance());
        }
        return groupConverter;
    }

    @Override
    public GroupDTO convert(Group group) {
        final GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(group.getId());
        groupDTO.setName(group.getName());
        if (group.getParentGroup() != null) {
            groupDTO.setParentId(group.getParentGroup().getId());
        }
        if(group.getSubGroups() != null){
            groupDTO.setSubGroups(this.convert(group.getSubGroups()));
        }
        if (group.getItems() != null) {
            groupDTO.setItems(this.itemConverter.bulkConvert(group.getItems()));
        }
        return groupDTO;
    }

    @Override
    public List<GroupDTO> convert(List<Group> groups) {
        return groups.stream().map(group -> {
            GroupDTO groupDTO = new GroupDTO();
            groupDTO.setId(group.getId());
            groupDTO.setName(group.getName());
            return groupDTO;
        }).collect(Collectors.toList());
    }
}
