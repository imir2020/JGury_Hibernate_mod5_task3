package servlets;

import dto.CreateUserDto;
import entity.Status;
import exception.ValidationException;

import lombok.extern.slf4j.Slf4j;
import service.UserService;
import utils.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("status", Status.values());
        req.getRequestDispatcher(JspHelper.getPath("registration")).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        var userDto = CreateUserDto.builder()
                .name(req.getParameter("name"))
                .birthday(req.getParameter("birthday"))
                .password(req.getParameter("pwd"))
                .status(req.getParameter("status"))
                .build();

        try {
            userService.create(userDto);
            log.info("The User with name {} and status {} was registered", userDto.getName(), userDto.getStatus());
            resp.sendRedirect("/login");
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            doGet(req,resp);
        }
    }
}
