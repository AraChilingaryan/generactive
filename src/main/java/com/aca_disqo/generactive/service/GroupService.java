package com.aca_disqo.generactive.service;


import com.aca_disqo.generactive.controller.dto.GroupDTO;
import com.aca_disqo.generactive.repository.model.Group;

import java.util.List;

public interface GroupService {

    Group create(GroupDTO groupDTO);

    Group get(Long id);

    Group update(Long id, GroupDTO groupDTO);

    List<Group> getAll();

    Boolean deleteById(Long id);

    Group findGroupByName(String name);

}
