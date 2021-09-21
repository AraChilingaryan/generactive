package com.aca_disqo.generactive.repository.jdbcimpl;

import com.aca_disqo.generactive.database.DatabaseConnection;
import com.aca_disqo.generactive.repository.ItemRepository;
import com.aca_disqo.generactive.repository.mapper.impl.ItemResultSetMapper;
import com.aca_disqo.generactive.repository.model.Group;
import com.aca_disqo.generactive.repository.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    @Override
    public List<Item> getAll() {
        List<Item> rv = new ArrayList<>();
        try (Connection connection = DatabaseConnection.initializeConnection()) {
            String query = "select * from generactive.item";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                rv.add(ItemResultSetMapper.mapToPojo(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return rv;
    }

    @Override
    public Item getItemById(Long id) {
        Item rv;
        try (Connection connection = DatabaseConnection.initializeConnection()) {
            String query = "select * from generactive.item where id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            rv = null;
            if (resultSet.next()) {
                rv = ItemResultSetMapper.mapToPojo(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return rv;
    }

    @Override
    public Item create(Item item) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Item> findItemByGroup(Group parentGroup) {
        List<Item> rv = new ArrayList<>();
        try (Connection connection = DatabaseConnection.initializeConnection()) {
            String query = "select * from generactive.item where name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, parentGroup.getName());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                rv.add(ItemResultSetMapper.mapToPojo(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return rv;
    }

    @Override
    public Item findHighestPricedItem() {
        Item rv = null;
        try (Connection connection = DatabaseConnection.initializeConnection()) {
            String query = "select max(base_price) from generactive.item";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                rv = ItemResultSetMapper.mapToPojo(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return rv;
    }

    @Override
    public List<Item> findItemsByPriceRange(int from, int to) {
        List<Item> rv = new ArrayList<>();
        try (Connection connection = DatabaseConnection.initializeConnection()) {
            String query = "select * from generactive.item where base_price between ? AND ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, from);
            statement.setLong(2, to);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                rv.add(ItemResultSetMapper.mapToPojo(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return rv;
    }
}
