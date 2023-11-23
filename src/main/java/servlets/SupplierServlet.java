package servlets;

import dto.SuppliersDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.SupplierService;
import utils.JspHelper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/suppliers")
public class SupplierServlet extends HttpServlet {
    private final SupplierService suppliersService = SupplierService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        var supplierList = suppliersService.findAll();
        req.setAttribute("supplierLis", supplierList);
        req.getRequestDispatcher(JspHelper.getPath("suppliers")).forward(req, resp);
    }
}
