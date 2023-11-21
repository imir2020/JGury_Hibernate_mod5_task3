package servlets;

import dao.CategoryDao;
import dto.CategoryDto;
import entity.Category;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CategoryService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {
    private final CategoryService categoryService = CategoryService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/hml");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        var writer = resp.getWriter();
        writer.write("<h1>Список категорий товаров</h1>");
        writer.write("<ul>");
        categoryService.findAll().forEach(categoryDto ->
                writer.write(
                        """
                                       <li>
                                         <h1>
                                         %d
                                         %s
                                         <br>
                                         </h1>
                                """.formatted(categoryDto.category(), categoryDto.categoryName())));

        writer.write("</ul>");
    }
}
