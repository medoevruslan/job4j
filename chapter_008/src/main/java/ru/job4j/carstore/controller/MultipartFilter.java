package ru.job4j.carstore.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Filter that manages multipart data.
 */
public class MultipartFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(MultipartFilter.class);

    @Override
    public void init(FilterConfig filterConfig) { }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (req.getMethod().equalsIgnoreCase("post")) {
            req = this.parseRequest(req);
        }
        filterChain.doFilter(req, servletResponse);
    }

    @Override
    public void destroy() { }

    private HttpServletRequest parseRequest(HttpServletRequest req) throws ServletException {
        HttpServletRequest request = req;
        int threshold = 1024 * 1024;
        final File tempDir = (File) req.getServletContext().getAttribute("javax.servlet.context.tempdir");
        if (ServletFileUpload.isMultipartContent(request)) {
            List<FileItem> multipartItems;
            try {
                DiskFileItemFactory factory = new DiskFileItemFactory(threshold, tempDir);
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setFileSizeMax(1024 * 1024 * 5);
                multipartItems = upload.parseRequest(request);
            } catch (FileUploadException fae) {
                LOG.error("Can't parse multipart request", fae);
                throw new ServletException("Can't parse multipart request" + fae.getMessage());
            }
            Map<String, String[]> parameterMap = new ConcurrentHashMap<>();
            List<FileItem> fileItems = new ArrayList<>();
            for (FileItem fileItem : multipartItems) {
                if (fileItem.isFormField()) {
                    processFormField(fileItem, parameterMap);
                } else {
                    processFileField(fileItem, fileItems);
                }
            }
            if (fileItems.size() != 0) {
                req.setAttribute(fileItems.get(0).getFieldName(), fileItems);
            }
            request =  this.wrapRequest(req, parameterMap);
        }
        return request;
    }

    private void processFormField(FileItem fileItem, Map<String, String[]> parameterMap) {
        String name = fileItem.getFieldName();
        String value = fileItem.getString();
        String[] values = parameterMap.get(name);
        if (values == null) {
            parameterMap.put(name, new String[] {value});
        } else {
            int length = values.length;
            String[] newValues = new String[length + 1];
            System.arraycopy(values, 0, newValues, 0, length);
            newValues[length] = value;
            parameterMap.put(name, newValues);
        }
    }

    private void processFileField(FileItem fileItem, List<FileItem> fileItems) {
        if (fileItem.getName().length() != 0) {
            fileItems.add(fileItem);
        }
    }

    private HttpServletRequest wrapRequest(HttpServletRequest req, final Map<String, String[]> parameterMap) {
        return new HttpServletRequestWrapper(req) {
            @Override
            public Map<String, String[]> getParameterMap() {
                return parameterMap;
            }
            @Override
            public String[] getParameterValues(String name) {
                return parameterMap.get(name);
            }
            @Override
            public String getParameter(String name) {
                String[] params = this.getParameterValues(name);
                return params != null && params.length > 0 ? params[0] : null;
            }
            @Override
            public Enumeration<String> getParameterNames() {
                return Collections.enumeration(parameterMap.keySet());
            }
        };
    }
}
