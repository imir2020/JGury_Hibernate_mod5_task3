package servlets;

import dto.ProductDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ProductService;
import utils.JspHelper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    private final ProductService productService = ProductService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long productId = Long.valueOf(req.getParameter("productId"));
        var product = productService.findById(productId);
        req.setAttribute("product", product);
        req.getRequestDispatcher(JspHelper.getPath("products")).forward(req, resp);
    }
}
