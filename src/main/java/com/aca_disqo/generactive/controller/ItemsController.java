package com.aca_disqo.generactive.controller;

import com.aca_disqo.generactive.context.ApplicationContext;
import com.aca_disqo.generactive.converter.ItemConverter;
import com.aca_disqo.generactive.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "itemsServlet", value = "/items-servlet/search")
public class ItemsController extends HttpServlet {

    private final ItemService itemService = ApplicationContext.getInstance().getItemService();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ItemConverter itemConverter = ApplicationContext.getInstance().getItemConverter();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int priceFrom = Integer.parseInt(req.getParameter("priceFrom"));
        int priceTo = Integer.parseInt(req.getParameter("priceTo"));
        resp.setContentType("application/json");
        resp.getWriter().write(objectMapper.writeValueAsString(itemConverter.bulkConvert(itemService.findItemsByPriceRange(priceFrom, priceTo))));
    }
}
