package org.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.HtmlGenerator;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/table")
public class ServletTable extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter printWriter = resp.getWriter();
        HtmlGenerator generator = new HtmlGenerator();

        printWriter.write(generator.tableMaker());
    }
}
