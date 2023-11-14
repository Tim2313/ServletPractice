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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String requestURI = req.getRequestURI();

        LOGGER.info("We enter on path {}", requestURI);


        Arguments arguments = new Arguments();
        arguments.getHashMap().put(RequestArgument.PATH, requestURI);

        Response response = pageMapService.getResponse(arguments);

        String contentType = response.getContentType();
        response.setContentType(contentType);

        int code = response.getStatusCode();
        response.setStatusCode(code);

        if (response.getAttributes() != null) {
            List<JspAttribute> attributes = response.getAttributes();
            for (JspAttribute attribute : attributes) {
                req.setAttribute(attribute.getName(), attribute.getValue());
            }
        }

        String jspPath = response.getJspPath();

        if (jspPath == null) {
            resp.getWriter().write(response.getBody());
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(jspPath);
        requestDispatcher.forward(req, resp);

    }


}
