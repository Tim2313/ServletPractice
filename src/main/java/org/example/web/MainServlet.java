package org.example.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.constant.RequestArgument;
import org.example.constant.HttpMapping;
import org.example.converter.JsonToArgumentsConverter;
import org.example.converter.RequestParametersToArguments;
import org.example.model.Arguments;
import org.example.model.JspAttribute;
import org.example.model.Response;
import org.example.service.InitializationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class MainServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainServlet.class);
    private final transient HttpMapper httpMapper = HttpMapper.getInstance();
    private final transient RequestParametersToArguments requestParametersToArguments = RequestParametersToArguments.getInstance();
    private final transient InitializationService initializationService = InitializationService.getInstance();
    private final transient  JsonToArgumentsConverter jsonToArgumentsConverter = JsonToArgumentsConverter.getInstance();

    @Override
    public void init() {
        initializationService.getInitialized();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String requestURI = req.getRequestURI();
        String requestHttpMethod = req.getMethod();

        LOGGER.info("We enter on path {}", requestURI);

        Arguments arguments = new Arguments();
        arguments.getHashMap().put(RequestArgument.HTTP_PATH, requestURI);
        arguments.getHashMap().put(RequestArgument.HTTP_METHOD, requestHttpMethod);

        Response response = httpMapper.getResponse(arguments);

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
            }
        } else {
            try {
                resp.getWriter().write(response.getBody());
            } catch (IOException e) {
                LOGGER.error("Error writing response body: {}", e.getMessage());
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        String requestURI = req.getRequestURI();
        String requestHttpMethod = req.getMethod();

        LOGGER.info(requestURI);
        LOGGER.info(requestHttpMethod);

        Arguments arguments = new Arguments();

        if (requestURI.equals(HttpMapping.POST_DEVELOPERS_REST.getWarUrl())) {
            arguments = jsonToArgumentsConverter.convert(req);
        }

        if (requestURI.equals(HttpMapping.POST_DEVELOPERS_HTML.getWarUrl())) {
            arguments = requestParametersToArguments.convert(req);
        }

        Response response = httpMapper.getResponse(arguments);

        String body = response.getBody();

        String contentType = response.getContentType();
        resp.setContentType(contentType);

        int code = response.getCode();
        resp.setStatus(code);

        if (body == null) {
            String redirectUrl = response.getRedirect().getWarUrl();
            try {
                resp.sendRedirect(redirectUrl);
            } catch (IOException e) {
                LOGGER.error("Wrong path: {} !", redirectUrl);
            }
        } else {

            try {
                resp.getWriter().write(body);
            } catch (IOException e) {
                LOGGER.error("Error writing response body: {}", e.getMessage());
            }
        }
    }
}
