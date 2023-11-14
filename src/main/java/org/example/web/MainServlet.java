package org.example.web;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.constant.RequestArguments;
import org.example.model.Arguments;
import org.example.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MainServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainServlet.class);
    private final PathMapper pageMapService = PathMapper.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestURI = req.getRequestURI();

        LOGGER.info("We enter on path {}", requestURI);

        Arguments arguments = new Arguments();
        arguments.getHashMap().put(RequestArguments.PATH, requestURI);

        Response response = pageMapService.getResponse(arguments);

        String contentType = response.getContentType();
        resp.setContentType(contentType);

        resp.getWriter().write(response.getBody());

        int code = response.getCode();
        resp.setStatus(code);
    }
}
