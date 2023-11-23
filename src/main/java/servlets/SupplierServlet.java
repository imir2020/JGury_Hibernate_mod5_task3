package servlets;

import dto.SuppliersDto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.SupplierService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/suppliers")
public class SupplierServlet extends HttpServlet {
    private final SupplierService suppliersService = SupplierService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        // Long productId = Long.valueOf(req.getParameter("productId"));
        // ProductDto productDto = productService.findById(productId);
        try (var writer = resp.getWriter()) {
            writer.write("<h1>Список поставщиков и их данные:</h1>");
            suppliersService.findAll().stream().forEach(suppliersDto ->
                    writer.write(
                            """
                                    <li>
                                    <h1>
                                    %d
                                    %s
                                    </h1>
                                    </li>
                                    """.formatted(suppliersDto.id(), suppliersDto.description())
                    ));
        }
    }
}
