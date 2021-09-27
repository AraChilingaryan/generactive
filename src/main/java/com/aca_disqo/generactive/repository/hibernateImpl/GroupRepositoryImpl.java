package com.aca_disqo.generactive.repository.hibernateImpl;

import com.aca_disqo.generactive.repository.GroupRepository;
import com.aca_disqo.generactive.repository.model.Group;
import com.aca_disqo.generactive.utils.databaseutil.HibernateConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class GroupRepositoryImpl implements GroupRepository {

    private static GroupRepository groupRepository = null;

    private GroupRepositoryImpl() {
    }

    public static GroupRepository getInstance() {
        if (groupRepository == null) {
            return new GroupRepositoryImpl();
        }
        return groupRepository;
    }

    public static final HibernateConfiguration HIBERNATE_CONFIGURATION =
            HibernateConfiguration.getInstance();


    @Override
    public Optional<Group> create(Group group) {
        Session session = HIBERNATE_CONFIGURATION.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(group);

        transaction.commit();

        session.close();

        return Optional.of(group);
    }

    public Optional<Group> get(Long groupId) {
        Session session = HIBERNATE_CONFIGURATION.getSession();
        Transaction transaction = session.beginTransaction();
        Group group = session.get(Group.class, groupId);

        transaction.commit();
        session.close();

        return Optional.ofNullable(group);
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
