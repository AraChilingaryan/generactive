package com.aca_disqo.generactive.controller;

import com.aca_disqo.generactive.config.ApplicationContainer;
import com.aca_disqo.generactive.controller.dto.ItemDTO;
import com.aca_disqo.generactive.controller.utils.ErrorEntity;
import com.aca_disqo.generactive.controller.utils.HttpConstants;
import com.aca_disqo.generactive.converter.ItemConverter;
import com.aca_disqo.generactive.converter.impl.ItemConverterImpl;
import com.aca_disqo.generactive.repository.model.Item;
import com.aca_disqo.generactive.service.ItemService;
import com.aca_disqo.generactive.service.impl.ItemServiceImpl;
import com.aca_disqo.generactive.utils.URLUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;


@WebServlet(name = "ItemServlet", urlPatterns = "/items/*")
public class ItemController extends HttpServlet {

    private final ItemService itemService =ApplicationContainer.applicationContext
            .getBean(ItemServiceImpl.class);
    private final ItemConverter itemConverter = ApplicationContainer.applicationContext
            .getBean(ItemConverterImpl.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = URLUtils.getLastPathSegment(req, resp);

        if (id == null) {
            resp.setStatus(400);
            resp.getWriter().write("Missing param " + id);
            return;
        }
        final ObjectMapper objectMapper = new ObjectMapper();
        final Item item = this.itemService.findItemBYId(id);
        resp.getWriter().write(objectMapper.writeValueAsString(itemConverter.convert(item)));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getContentType().equals(HttpConstants.ContentType.APPLICATION_JSON)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "not_supported_format");
        }

        final ObjectMapper objectMapper = new ObjectMapper();
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
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = URLUtils.getLastPathSegment(req, resp);
        if (!req.getContentType().equals(HttpConstants.ContentType.APPLICATION_JSON)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "not_supported_format");
        }

        this.itemService.deleteById(id);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = URLUtils.getLastPathSegment(req, resp);
        if (!req.getContentType().equals(HttpConstants.ContentType.APPLICATION_JSON)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "not_supported_format");
        }

        final ObjectMapper objectMapper = new ObjectMapper();
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
