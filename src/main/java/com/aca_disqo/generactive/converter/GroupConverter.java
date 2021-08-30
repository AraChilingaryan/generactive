package com.aca_disqo.generactive.converter;


import com.aca_disqo.generactive.controller.dto.GroupDTO;
import com.aca_disqo.generactive.repository.model.Group;

public interface GroupConverter {

    GroupDTO convert(Group group);
}
