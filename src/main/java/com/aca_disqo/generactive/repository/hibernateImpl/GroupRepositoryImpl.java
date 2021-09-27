package com.aca_disqo.generactive.repository.hibernateImpl;

import com.aca_disqo.generactive.repository.GroupRepository;
import com.aca_disqo.generactive.repository.model.Group;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GroupRepositoryImpl implements GroupRepository {

    private final SessionFactory sessionFactory;

    public GroupRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Optional<Group> create(Group group) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(group);

        transaction.commit();

        session.close();

        return Optional.of(group);
    }

    public Optional<Group> get(Long groupId) {
        Session session = sessionFactory.getCurrentSession();
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
