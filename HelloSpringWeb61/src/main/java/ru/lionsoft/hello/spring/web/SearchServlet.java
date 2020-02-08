/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.WebApplicationContext;
import ru.lionsoft.hello.spring.web.catalog.Catalog;
import ru.lionsoft.hello.spring.web.entity.MusicItem;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Read parameter from HTML form
        String keyword = request.getParameter("keyword");

        // Do the search, store results in request with name "results"
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        Catalog cat = (Catalog) ctx.getBean("javaTunesCatalog");
        Collection<MusicItem> results = cat.findByKeyword(keyword);
        request.setAttribute("results", results);

        // Forward to /jsp/searchResults.jsp for display
        this.getServletContext()
                .getRequestDispatcher("/jsp/searchResults.jsp")
                .forward(request, response);
    }
}
