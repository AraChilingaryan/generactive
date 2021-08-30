package com.aca_disqo.generactive.converter.impl;


import com.aca_disqo.generactive.controller.dto.GroupDTO;
import com.aca_disqo.generactive.converter.GroupConverter;
import com.aca_disqo.generactive.converter.ItemConverter;
import com.aca_disqo.generactive.repository.model.Group;
import com.aca_disqo.generactive.repository.model.Item;

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
        if (group.getSubGroups() != null) {
            groupDTO.setSubGroups(group.getSubGroups().stream().map(Group::getId).collect(Collectors.toList()));
        }
        if (group.getItems() != null) {
            groupDTO.setItems(group.getItems().stream().map(Item::getId).collect(Collectors.toList()));
        }
        return groupDTO;
    }
}
