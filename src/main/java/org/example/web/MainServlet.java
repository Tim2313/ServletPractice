package org.example.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.constant.RequestArgument;
import org.example.converter.JsonToArgumentsConverter;
import org.example.converter.RequestParametersToArguments;
import org.example.model.Arguments;
import org.example.model.JspAttribute;
import org.example.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class MainServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainServlet.class);
    private final PathMapper pathMapper = PathMapper.getInstance();
    private final RequestParametersToArguments requestParametersToArguments = RequestParametersToArguments.getInstance();
    private final JsonToArgumentsConverter jsonToArgumentsConverter = JsonToArgumentsConverter.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String requestURI = req.getRequestURI();

        RequestArgument.FIRSTNAME.getRequestArgument();

        String requestHttpMethod = req.getMethod();

        LOGGER.info("We enter on path {}", requestURI);

        Arguments arguments = new Arguments();
        arguments.getHashMap().put(RequestArgument.HTTP_PATH, requestURI);
        arguments.getHashMap().put(RequestArgument.HTTP_METHOD, requestHttpMethod);

        Response response = pathMapper.getResponse(arguments);

        String contentType = response.getContentType();
        resp.setContentType(contentType);

        int code = response.getCode();
        resp.setStatus(code);

        String jspPage = response.getJspPage();

        if (jspPage != null) {
            if (response.getJspAttributes() != null) {
                List<JspAttribute> attributes = response.getJspAttributes();
                for (JspAttribute attribute : attributes) {
                    req.setAttribute(attribute.getName(), attribute.getValue());
                }
            }
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(jspPage);
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                LOGGER.error("Failed to forward to jsp '{}'. Error: {}", jspPage, e.getMessage());
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String requestURI = req.getRequestURI();
        String requestHttpMethod = req.getMethod();

        LOGGER.info(requestURI);
        LOGGER.info(requestHttpMethod);

        Arguments arguments = jsonToArgumentsConverter.convert(req);

        Response response = pathMapper.getResponse(arguments);

        String body = response.getBody();

        String contentType = response.getContentType();
        resp.setContentType(contentType);

        int code = response.getCode();
        resp.setStatus(code);

        if (body == null) {
            String redirectUrl = response.getRedirect().getFullUrl();

            try {
                resp.sendRedirect(redirectUrl);
            } catch (IOException e) {
                LOGGER.error("Wrong path: {} !", redirectUrl);
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        } else {

            try {
                resp.getWriter().write(body);
            } catch (IOException e) {
                LOGGER.error("Error writing response body: {}", e.getMessage());
                e.printStackTrace();
                throw new RuntimeException();
            }

        }
    }
}
