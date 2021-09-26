package com.aca_disqo.generactive.controller;

import com.aca_disqo.generactive.context.ApplicationContext;
import com.aca_disqo.generactive.controller.dto.GroupDTO;
import com.aca_disqo.generactive.controller.utils.ErrorEntity;
import com.aca_disqo.generactive.controller.utils.HttpConstants;
import com.aca_disqo.generactive.converter.GroupConverter;
import com.aca_disqo.generactive.repository.model.Group;
import com.aca_disqo.generactive.service.GroupService;
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


@WebServlet(name = "GroupServlet", urlPatterns = "/groups/*")
public class GroupController extends HttpServlet {

    private final GroupService groupService = ApplicationContext.getInstance().getGroupService();
    private final GroupConverter converter = ApplicationContext.getInstance().getGroupConverter();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Long groupId = URLUtils.getLastPathSegment(req, resp);
        resp.getWriter().write(objectMapper.writeValueAsString(converter.convert(this.groupService.get(groupId))));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        if (!req.getContentType().equals(HttpConstants.ContentType.APPLICATION_JSON)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "not_supported_format");
        }

        BufferedReader bufferedReader = req.getReader();
        String body = bufferedReader.lines().collect(Collectors.joining());

        try {
            GroupDTO group = objectMapper.readValue(body, GroupDTO.class);
            Group group1 = groupService.create(group);
            resp.getWriter().write(objectMapper.writeValueAsString(converter.convert(group1)));
        } catch (RuntimeException e) {
            ErrorEntity errorEntity = new ErrorEntity("json_parse_failed:" + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(objectMapper.writeValueAsString(errorEntity));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = URLUtils.getLastPathSegment(req, resp);
        this.groupService.deleteById(id);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = URLUtils.getLastPathSegment(req, resp);
        final ObjectMapper objectMapper = new ObjectMapper();
        if (!req.getContentType().equals(HttpConstants.ContentType.APPLICATION_JSON)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "not_supported_format");
        }

        BufferedReader bufferedReader = req.getReader();
        String body = bufferedReader.lines().collect(Collectors.joining());

        try {
            GroupDTO group = objectMapper.readValue(body, GroupDTO.class);
            Group group1 = groupService.update(id, group);
            resp.getWriter().write(objectMapper.writeValueAsString(converter.convert(group1)));
        } catch (RuntimeException e) {
            ErrorEntity errorEntity = new ErrorEntity("json_parse_failed:" + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(objectMapper.writeValueAsString(errorEntity));
        }
    }
}
