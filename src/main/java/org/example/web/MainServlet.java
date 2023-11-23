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

        String contentType = response.getContentType();
        response.setContentType(contentType);

        int code = response.getCode();
        response.setCode(code);

        if (response.getJspAttributes() != null) {
            List<JspAttribute> attributes = response.getJspAttributes();
            for (JspAttribute attribute : attributes) {
                req.setAttribute(attribute.getName(), attribute.getValue());
            }
        }

        String jspPath = response.getJspPage();


        if (jspPath != null) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(jspPath);
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                LOGGER.error("Failed to forward to jsp '{}'. Error: {}", jspPath, e.getMessage());
                e.printStackTrace();
                throw new RuntimeException();
            }
        } else {
            try {
                resp.getWriter().write(response.getBody());
            } catch (IOException e) {
                LOGGER.error("Error writing response body: {}", e.getMessage());
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
    }
}
