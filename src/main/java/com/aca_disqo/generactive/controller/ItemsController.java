package com.aca_disqo.generactive.controller;

import com.aca_disqo.generactive.config.ApplicationContainer;
import com.aca_disqo.generactive.converter.ItemConverter;
import com.aca_disqo.generactive.converter.impl.ItemConverterImpl;
import com.aca_disqo.generactive.service.ItemService;
import com.aca_disqo.generactive.service.impl.ItemServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "itemsServlet", urlPatterns = "/items-servlet/search")
public class ItemsController extends HttpServlet {

    private final ItemService itemService = ApplicationContainer.applicationContext
            .getBean(ItemServiceImpl.class);
    private final ItemConverter itemConverter = ApplicationContainer.applicationContext
            .getBean(ItemConverterImpl.class);
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int priceFrom = Integer.parseInt(req.getParameter("priceFrom"));
        int priceTo = Integer.parseInt(req.getParameter("priceTo"));
        resp.setContentType("application/json");
        resp.getWriter().write(objectMapper.writeValueAsString(itemConverter.bulkConvert(itemService.findItemsByPriceRange(priceFrom, priceTo))));
    }
}
