package servlets;

import dto.ProductDto;


import service.ProductService;
import utils.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
