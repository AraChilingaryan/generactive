package com.aca_disqo.generactive.controller;

import com.aca_disqo.generactive.context.ApplicationContext;
import com.aca_disqo.generactive.controller.utils.HttpConstants;
import com.aca_disqo.generactive.converter.ItemConverter;
import com.aca_disqo.generactive.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "itemsServletForGroup", urlPatterns = "/items-servlet/getByGroup")
public class ItemServletGetByGroup extends HttpServlet {
    private final ItemService itemService = ApplicationContext.getInstance().getItemService();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ItemConverter itemConverter = ApplicationContext.getInstance().getItemConverter();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String group_id = req.getParameter("group_id");

        if (group_id == null || group_id.isEmpty()) {
            resp.setStatus(400);
            resp.getWriter().write("Missing param " + group_id);
            return;
        }
        resp.setContentType(HttpConstants.ContentType.APPLICATION_JSON);
        resp.getWriter().write(objectMapper
                .writeValueAsString(itemConverter.bulkConvert(itemService
                        .findItemByGroup(Long.parseLong(group_id)))));
    }
}

