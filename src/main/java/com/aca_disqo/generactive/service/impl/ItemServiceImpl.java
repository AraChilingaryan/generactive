package com.aca_disqo.generactive.service.impl;

import com.aca_disqo.generactive.context.ApplicationContext;
import com.aca_disqo.generactive.controller.dto.ItemDTO;
import com.aca_disqo.generactive.controller.enums.ItemClientType;
import com.aca_disqo.generactive.repository.ItemRepository;
import com.aca_disqo.generactive.repository.model.Generative;
import com.aca_disqo.generactive.repository.model.Group;
import com.aca_disqo.generactive.repository.model.Item;
import com.aca_disqo.generactive.repository.model.Stock;
import com.aca_disqo.generactive.service.GroupService;
import com.aca_disqo.generactive.service.ItemService;
import com.aca_disqo.generactive.utils.Currency;

import java.util.List;

public class ItemServiceImpl implements ItemService {

    private final GroupService groupService;
    private final ItemRepository itemRepository;
    private static ItemService itemService;

    private ItemServiceImpl(GroupService groupService1, ItemRepository itemRepository) {
        this.groupService = groupService1;
        this.itemRepository = itemRepository;
    }


    public static ItemService getInstance(){
        if (itemService == null) {
            itemService = new ItemServiceImpl(ApplicationContext.getInstance().getGroupService(),
                    ApplicationContext.getInstance().getItemRepository());
        }
        return itemService;
    }

    @Override
    public List<Item> findALl() {
        return this.itemRepository.getAll();
    }

    @Override
    public Item findItemBYId(Long id) {
        return this.itemRepository.getItemById(id);
    }

    @Override
    public Item create(ItemDTO itemDto) {
        final Item item = buildItemFrom(itemDto);
        Group group = groupService.get(item.getGroup().getId());
        group.getItems().add(item);
        itemRepository.create(item);
        return item;
    }

    @Override
    public Item update(Long id, ItemDTO itemDTO) {
        final Item item = this.itemRepository.getItemById(id);
        item.setBasePrice(itemDTO.getPrice());
        item.setCurrency(convertCurrency(itemDTO.getCurrency()));
        item.setGroup(groupService.get(itemDTO.getGroupId()));
        item.setImageUrl(itemDTO.getImageUrl());
        item.setName(itemDTO.getName());
        return item;
    }

    @Override
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public List<Item> findItemsByPriceRange(int priceFrom, int priceTo) {
       return this.itemRepository.findItemsByPriceRange(priceFrom, priceTo);
    }

    @Override
    public List<Item> findItemByGroup(Long parentGroupId) {
        return itemRepository.findItemByGroup(groupService.get(parentGroupId));
    }

    @Override
    public Item findHighestPricedItem() {
        return itemRepository.findHighestPricedItem();
    }


    private Item buildItemFrom(ItemDTO itemDTO) {
        Item item = null;
        if (itemDTO.getItemClientType().equals(ItemClientType.GENERATIVE)) {
            item = new Generative();
        } else if (itemDTO.getItemClientType().equals(ItemClientType.STOCK)) {
            item = new Stock();
        }
        assert item != null;
        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setCurrency(convertCurrency(itemDTO.getCurrency()));
        item.setGroup(groupService.get(itemDTO.getGroupId()));
        item.setBasePrice(itemDTO.getPrice());
        return item;
    }

    private Currency convertCurrency(String currencyType) {
        return Currency.valueOf(currencyType);
    }
}
