package org.example.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.constant.RequestArgument;
import org.example.model.Arguments;
import org.example.model.JspAttribute;
import org.example.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class MainServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainServlet.class);
    private final PathMapper pageMapService = PathMapper.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String requestURI = req.getRequestURI();

        LOGGER.info("We enter on path {}", requestURI);


        Arguments arguments = new Arguments();
        arguments.getHashMap().put(RequestArgument.PATH, requestURI);

        Response response = pageMapService.getResponse(arguments);

        String contentType = response.getResponseContentType();
        response.setResponseContentType(contentType);

        int code = response.getResponseCode();
        response.setResponseCode(code);

        if (response.getResponseAttributes() != null) {
            List<JspAttribute> attributes = response.getResponseAttributes();
            for (JspAttribute attribute : attributes) {
                req.setAttribute(attribute.getName(), attribute.getValue());
            }
        }

        String jspPath = response.getResponseJspAttributes();

        if (jspPath == null) {
            try {
                resp.getWriter().write(response.getResponseBody());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(jspPath);
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
