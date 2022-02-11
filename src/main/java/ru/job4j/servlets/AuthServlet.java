package ru.job4j.servlets;

import ru.job4j.model.User;
import ru.job4j.store.HbmStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                                        throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = HbmStore.instance().findUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            HttpSession sc = req.getSession();
            sc.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            req.setAttribute("error",
                    "Такого пользователя не сущетсвует или не правильно введены данные");
            req.getRequestDispatcher("auth.jsp").forward(req, resp);
        }
    }
}
