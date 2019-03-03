package ru.job4j.carstore.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.file.Files;

/**
 * Controller manages image requests.
 */
public class Image extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestedImage = req.getPathInfo();
        File file = new File(URLDecoder.decode(requestedImage, "UTF8"));
        if (file.exists()) {
            String contentType = getServletContext().getMimeType(file.getName());
            resp.setContentType(contentType);
            resp.setHeader("Content-length", String.valueOf(file.length()));
            Files.copy(file.toPath(), resp.getOutputStream());
        } else {
            PrintWriter writer = resp.getWriter();
            writer.write("");
            writer.flush();
        }
    }
}
