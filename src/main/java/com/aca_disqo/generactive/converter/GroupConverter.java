package com.aca_disqo.generactive.converter;

import com.aca_disqo.generactive.controller.dto.GroupDTO;
import com.aca_disqo.generactive.repository.model.Group;

import java.util.List;

public interface GroupConverter {

    GroupDTO convert(Group group);

    List<GroupDTO> convert(List<Group> groups);
}
