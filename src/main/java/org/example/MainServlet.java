package org.example;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Developer;
import org.example.model.UrlPattern;
import org.example.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class MainServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainServlet.class);
    private final HelloService helloService = HelloService.getInstance();
    private final TableService tableService = TableService.getInstance();
    private final NotFoundService notFoundService = NotFoundService.getInstance();
    private final JsonService jsonObjectService = JsonService.getInstance();
    private final DeveloperService developerService = DeveloperService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String requestURI = req.getRequestURI();

        LOGGER.info("We enter on path {}", requestURI);
        
        if (requestURI.equals(UrlPattern.HTML.getUrl())) {
            resp.setContentType("text/html");
            List<Developer> developers = developerService.getDevelopers();
            String tableResponse = tableService.getDevelopersTable(developers);
            resp.getWriter().write(tableResponse);
            resp.setStatus(200);
            LOGGER.info("'Html' page has showed!");
        } else if (requestURI.equals(UrlPattern.HELLO.getUrl())) {
            resp.setContentType("text/html");
            String helloResponse = helloService.getHelloResponse();
            resp.getWriter().write(helloResponse);
            resp.setStatus(200);
            LOGGER.info("'Hello' page has showed!");
        } else if (requestURI.equals(UrlPattern.JSON.getUrl())) {
            resp.setContentType("application/json");
            List<Developer> developers = developerService.getDevelopers();
            String jsonResponse = jsonObjectService.getDevelopers(developers);
            resp.getWriter().write(jsonResponse);
            resp.setStatus(200);
            LOGGER.info("'Json' page has showed!");
        } else {
            resp.setContentType("text/html");
            String notFoundMessage = notFoundService.getNotFoundResponse();
            resp.getWriter().write(notFoundMessage);
            resp.setStatus(404);
            LOGGER.info("'NotFoundResponse' page has showed!");
        }
    }

}


