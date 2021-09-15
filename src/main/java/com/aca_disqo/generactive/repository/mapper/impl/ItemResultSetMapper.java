package com.aca_disqo.generactive.repository.mapper.impl;

import com.aca_disqo.generactive.repository.model.Item;
import com.aca_disqo.generactive.repository.model.Stock;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemResultSetMapper {

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_BASE_PRICE = "base_price";

    public static Item mapToPojo(ResultSet resultSet) throws SQLException {
        return mapToPojo(resultSet, null);
    }

    public static Item mapToPojo(ResultSet resultSet, String alias) throws SQLException {
        alias = alias == null ? "" : alias + "_";
        Long id = resultSet.getLong(alias + COLUMN_ID);
        String name = resultSet.getString(alias + COLUMN_NAME);
        int basePrice = resultSet.getInt(alias + COLUMN_BASE_PRICE);
        return new Stock(id, basePrice, name);
    }
}

