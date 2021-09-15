package com.aca_disqo.generactive.controller;

import com.aca_disqo.generactive.context.ApplicationContext;
import com.aca_disqo.generactive.controller.dto.ItemDTO;
import com.aca_disqo.generactive.controller.utils.ErrorEntity;
import com.aca_disqo.generactive.controller.utils.HttpConstants;
import com.aca_disqo.generactive.converter.ItemConverter;
import com.aca_disqo.generactive.repository.model.Item;
import com.aca_disqo.generactive.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;


@WebServlet(name = "itemsServlet", value = "/items/*")
public class ItemController extends HttpServlet {

    private final ItemService itemService = ApplicationContext.getInstance().getItemService();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private ItemConverter itemConverter = ApplicationContext.getInstance().getItemConverter();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if (id == null || id.isEmpty()) {
            resp.setStatus(400);
            resp.getWriter().write("Missing param " + id);
            return;
        }
        final Item item = this.itemService.findItemBYId(Long.parseLong(id));
        resp.getWriter().write(objectMapper.writeValueAsString(itemConverter.convert(item)));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getContentType().equals(HttpConstants.ContentType.APPLICATION_JSON)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "not_supported_format");
        }

        BufferedReader bufferedReader = req.getReader();
        String body = bufferedReader.lines().collect(Collectors.joining());

        try {
            ItemDTO item = objectMapper.readValue(body, ItemDTO.class);
            Item item1 = itemService.create(item);
            resp.getWriter().write(objectMapper.writeValueAsString(itemConverter.convert(item1)));
        } catch (RuntimeException e) {
            ErrorEntity errorEntity = new ErrorEntity("json_parse_failed:" + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(objectMapper.writeValueAsString(errorEntity));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) {
        String id = req.getParameter("id");
        this.itemService.deleteById(Long.parseLong(id));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        if (!req.getContentType().equals(HttpConstants.ContentType.APPLICATION_JSON)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "not_supported_format");
        }

        BufferedReader bufferedReader = req.getReader();
        String body = bufferedReader.lines().collect(Collectors.joining());

        try {
            ItemDTO item = objectMapper.readValue(body, ItemDTO.class);
            Item item1 = itemService.update(id,item);
            resp.getWriter().write(objectMapper.writeValueAsString(itemConverter.convert(item1)));
        } catch (RuntimeException e) {
            ErrorEntity errorEntity = new ErrorEntity("json_parse_failed:" + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(objectMapper.writeValueAsString(errorEntity));
        }
    }
}
