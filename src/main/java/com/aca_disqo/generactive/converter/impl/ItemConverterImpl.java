package com.aca_disqo.generactive.converter.impl;


import com.aca_disqo.generactive.controller.dto.ItemDTO;
import com.aca_disqo.generactive.converter.ItemConverter;
import com.aca_disqo.generactive.repository.model.Item;

import java.util.List;
import java.util.stream.Collectors;

public class ItemConverterImpl implements ItemConverter {

    private static ItemConverter itemConverter;

    private ItemConverterImpl() {

    }

    public static ItemConverter getInstance() {
        if (itemConverter == null) {
            itemConverter = new ItemConverterImpl();
        }
        return itemConverter;
    }

    @Override
    public List<ItemDTO> bulkConvert(List<Item> items) {
        return items.stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public ItemDTO convert(final Item item) {
        final ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setPrice(item.getBasePrice());
        itemDTO.setGroupId(item.getGroup().getId());
        return itemDTO;
    }

    @Override
    public ItemDTO convertFromCSV(final String[] csvLine) {
        final ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(Long.parseLong(csvLine[0]));
        itemDTO.setPrice(Integer.parseInt(csvLine[1]));
        itemDTO.setName(csvLine[2]);
        itemDTO.setImageUrl(csvLine[3]);
        itemDTO.setGroupId(Long.parseLong(csvLine[4]));
        return itemDTO;
    }
}
