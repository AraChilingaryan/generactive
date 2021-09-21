package com.aca_disqo.generactive.repository.hibernateImpl;

import com.aca_disqo.generactive.repository.GroupRepository;
import com.aca_disqo.generactive.repository.model.Group;

import java.util.List;
import java.util.Optional;

public class GroupRepositoryImpl implements GroupRepository {
    @Override
    public Optional<Group> create(Group group) {
        return Optional.empty();
    }

    @Override
    public Optional<Group> get(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Group> getAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<Group> findGroupByName(String name) {
        return Optional.empty();
    }
}
