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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {
    private final CategoryService categoryService = null;//CategoryService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (var writer = resp.getWriter()) {

            writer.write("<h1>Список категорий товаров</h1>");
           // writer.write("<ul>");
           // writer.write("<h2>Список товаров</h2>");
           List<Integer> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                list.add(i);
            }

            list.stream().forEach(integer ->
                    writer.write(
                            """
                                    <li>       
                                    <h1>         
                                    %s          
                                    </h>
                                    </li>
                                    """.formatted(integer)));

        }
    }

    public static void main(String[] args) {
        CategoryService categoryService = CategoryService.getInstance();
        System.out.println(categoryService);
    }
}
