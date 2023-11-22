package servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CategoryService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/o")
public class Test extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/hml");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        var writer = resp.getWriter();
        writer.write("<h1>Список категорий товаров</h1>");
        writer.close();
    }
}
