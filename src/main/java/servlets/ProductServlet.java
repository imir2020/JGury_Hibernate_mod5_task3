package servlets;

import dto.ProductDto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ProductService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    private final ProductService productService = ProductService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        //Нужно передать в request -  Long productId ? - то есть номер продукта
        // должен приходить как запрос от клиента
        Long productId = Long.valueOf(req.getParameter("productId"));
        ProductDto productDto = productService.findById(productId);
        try (var writer = resp.getWriter()) {
            writer.write("<h1>Название продукта по id</h1>");
            writer.write(("""
                           <h1>
                           %d
                           %s
                           </h1>
                           """.formatted(productDto.id(),
                    productDto.nameAndCount())));
        }
    }
}
