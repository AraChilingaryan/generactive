package com.aca_disqo.generactive.repository.jdbcimpl;

import com.aca_disqo.generactive.database.DatabaseConnection;
import com.aca_disqo.generactive.repository.GroupRepository;
import com.aca_disqo.generactive.repository.mapper.impl.GroupResultSetMapper;
import com.aca_disqo.generactive.repository.mapper.impl.ItemResultSetMapper;
import com.aca_disqo.generactive.repository.model.Group;
import com.aca_disqo.generactive.repository.model.Item;

import java.sql.*;
import java.util.ArrayList;
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

    @Override
    public Optional<Group> create(Group group) {
        Group result = null;
        try (Connection connection = DatabaseConnection.initializeConnection()) {
            Long parentGroup_id = group.getParentGroup() == null ? null : group.getParentGroup().getId();
            String query = "insert into generactive.groups (name, parent_id) values("
                    + "'" + group.getName()
                    + "','" + parentGroup_id
                    + "')";

            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                result = GroupResultSetMapper.mapToPojo(resultSet);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return result == null ? Optional.empty() : Optional.of(result);
    }

    @Override
    public Optional<Group> get(Long id) {
        Group group = null;
        try (Connection connection = DatabaseConnection.initializeConnection()) {
            String query = "SELECT g.id as group_id, g.name as group_name, i.id, i.name, i.base_price FROM \"groups\" g left join item i on g.id = i.group_id where g.id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (group == null) {
                    group = GroupResultSetMapper.mapToPojo(resultSet);
                }
                Item item = ItemResultSetMapper.mapToPojo(resultSet);
                group.getItems().add(item);
            }
            return group == null ? Optional.empty() : Optional.of(group);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Group> getAll() {
        List<Group> groups = new ArrayList<>();
        try (Connection connection = DatabaseConnection.initializeConnection()) {
            String query = "SELECT * from groups";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                groups.add(GroupResultSetMapper.mapToPojo(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return groups;
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = DatabaseConnection.initializeConnection()) {
            String query = "DELETE from groups where id = 1?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Group> findGroupByName(String name) {
        Group group = null;
        try (Connection connection = DatabaseConnection.initializeConnection()) {
            String query = "SELECT * from groups where name = 1?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.executeUpdate();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                group = GroupResultSetMapper.mapToPojo(resultSet);
            }
            return group == null ? Optional.empty() : Optional.of(group);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
