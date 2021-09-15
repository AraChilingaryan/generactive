package com.aca_disqo.generactive.repository;

import com.aca_disqo.generactive.repository.model.Group;

import java.util.List;
import java.util.Optional;

public interface GroupRepository {

    Optional<Group> create(Group group);

    Optional<Group> get(Long id);

    List<Group> getAll();

    void deleteById(Long id);

    Optional<Group> findGroupByName(String name);

}
