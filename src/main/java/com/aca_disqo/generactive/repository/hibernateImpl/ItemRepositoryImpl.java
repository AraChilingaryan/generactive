package com.aca_disqo.generactive.repository.hibernateImpl;

import com.aca_disqo.generactive.repository.ItemRepository;
import com.aca_disqo.generactive.repository.model.Group;
import com.aca_disqo.generactive.repository.model.Item;
import com.aca_disqo.generactive.utils.databaseutil.HibernateConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class ItemRepositoryImpl implements ItemRepository {
    private static ItemRepository itemRepository = null;

    private ItemRepositoryImpl() {
    }

    public static ItemRepository getInstance() {
        if (itemRepository == null) {
            itemRepository = new ItemRepositoryImpl();
        }
        return itemRepository;
    }

    public static final HibernateConfiguration HIBERNATE_CONFIGURATION =
            HibernateConfiguration.getInstance();

    @Override
    public List<Item> getAll() {
        Session session = HIBERNATE_CONFIGURATION.getSession();
        Transaction transaction = session.beginTransaction();
        List<Item> items = session.createQuery("SELECT a FROM Item a", Item.class).getResultList();

        transaction.commit();
        session.close();
        return items;
    }

    @Override
    public Item getItemById(Long id) {
        Session session = HIBERNATE_CONFIGURATION.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Item i WHERE i.id = :id");
        query.setParameter("id", id);

        Item item = (Item)query.getSingleResult();

        transaction.commit();
        session.close();
        return item;
    }

    @Override
    public Item save(Item item) {
        Session session = HIBERNATE_CONFIGURATION.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(item);

        transaction.commit();
        session.close();
        return item;
    }

    @Override
    public boolean deleteById(Long id) {
        Session session = HIBERNATE_CONFIGURATION.getSession();
        Transaction transaction = session.beginTransaction();
        String q = "delete from Item i" +
                " where i.id = :id";
        Query query = session.createQuery(q);
        query.setParameter("id", id);
        int deleted = query.executeUpdate();

        transaction.commit();
        session.close();

        return deleted != 0;
    }

    @Override
    public List<Item> findItemByGroup(Group parentGroup) {
        Session session = HIBERNATE_CONFIGURATION.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Item i WHERE i.group = :group");
        query.setParameter("group", parentGroup);

        List<Item> items = query.getResultList();

        transaction.commit();
        session.close();
        return items;
    }

    @Override
    public Item findHighestPricedItem() {
        Session session = HIBERNATE_CONFIGURATION.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Item i WHERE i.basePrice = (select max(ii.basePrice) from Item ii)");

        Item item = (Item)query.getSingleResult();

        transaction.commit();
        session.close();
        return item;
    }

    @Override
    public List<Item> findItemsByPriceRange(int from, int to) {
        Session session = HIBERNATE_CONFIGURATION.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Item i WHERE i.basePrice > :from AND i.basePrice < :to");
        query.setParameter("from", from);
        query.setParameter("to", to);

        List<Item> items = query.getResultList();

        transaction.commit();
        session.close();
        return items;
    }

    @Override
    public Item update(Long id, Item item) {
        Session session = HIBERNATE_CONFIGURATION.getSession();
        Transaction transaction = session.beginTransaction();
        Item existingItem = session.get(Item.class, id);
        existingItem.setGroup(item.getGroup());
        existingItem.setBasePrice(item.getBasePrice());
        existingItem.setName(item.getName());

        transaction.commit();
        session.close();
        return item;
    }
}
