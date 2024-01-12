package servlets;

import service.SupplierService;
import utils.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/suppliers")
public class SupplierServlet extends HttpServlet {
    private final SupplierService suppliersService = SupplierService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        var supplierList = suppliersService.findAll();
        req.setAttribute("supplierList", supplierList);
        req.getRequestDispatcher(JspHelper.getPath("suppliers")).forward(req, resp);
    }
}
