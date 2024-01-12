package servlets;

import service.CategoryService;
import utils.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {
    private final CategoryService categoryService = CategoryService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        var categories = categoryService.findAll();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher(JspHelper.getPath("categories")).forward(req, resp);
    }
}
