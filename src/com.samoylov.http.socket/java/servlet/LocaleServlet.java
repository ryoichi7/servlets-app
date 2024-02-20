package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static util.UrlPath.*;

@WebServlet("/locale")
public class LocaleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var lang = req.getParameter("lang");
        req.getSession().setAttribute("lang", lang);

        var prevPage = req.getHeader("referer");
        var page = prevPage != null ? prevPage : LOGIN;
        String url;

        if (!page.contains("?lang=")){
            url = "%s?lang=%s".formatted(page, lang);
        } else {
            url = page.substring(0, page.length() - 5) + lang;
        }
        resp.sendRedirect(url);
    }
}
