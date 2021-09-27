package com.aca_disqo.generactive.context;

import com.aca_disqo.generactive.converter.GroupConverter;
import com.aca_disqo.generactive.converter.ItemConverter;
import com.aca_disqo.generactive.converter.impl.GroupConverterImpl;
import com.aca_disqo.generactive.converter.impl.ItemConverterImpl;
import com.aca_disqo.generactive.repository.GroupRepository;
import com.aca_disqo.generactive.repository.ItemRepository;
import com.aca_disqo.generactive.repository.hibernateImpl.GroupRepositoryImpl;
import com.aca_disqo.generactive.repository.hibernateImpl.ItemRepositoryImpl;
import com.aca_disqo.generactive.service.GroupService;
import com.aca_disqo.generactive.service.ItemService;
import com.aca_disqo.generactive.service.impl.GroupServiceImpl;
import com.aca_disqo.generactive.service.impl.ItemServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private static Map<Class, Object> registry = new HashMap<>();
    static {
        registry.put(GroupServiceImpl.class, GroupServiceImpl.getInstance());
    }

    public static <T> T getInstance(Class<T> clazz) {
        return (T)registry.get(clazz);
    }

    private static ApplicationContext applicationContext = null;

    public static ApplicationContext getInstance() {
        if (applicationContext == null) {
            applicationContext = new ApplicationContext();
        }
        return applicationContext;
    }

    public GroupService getGroupService() {
        return GroupServiceImpl.getInstance();
    }

    public ItemService getItemService() {
        return ItemServiceImpl.getInstance();
    }

    public GroupConverter getGroupConverter() {
        return GroupConverterImpl.getInstance();
    }

    public ItemConverter getItemConverter() {
        return ItemConverterImpl.getInstance();
    }

    public GroupRepository getGroupRepository() {
        return GroupRepositoryImpl.getInstance();
    }

    public ItemRepository getItemRepository() {
        return ItemRepositoryImpl.getInstance();
    }

}
