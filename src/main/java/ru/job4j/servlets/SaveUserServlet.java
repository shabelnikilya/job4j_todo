package ru.job4j.servlets;

import ru.job4j.model.User;
import ru.job4j.store.HbmStore;
import ru.job4j.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                                    throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String secondName = req.getParameter("secondName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Store store = HbmStore.instance();
        if (store.findUserByEmail(email) == null) {
            store.saveUser(new User(name, secondName, email, password));
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Пользователь с данной почтой уже зарегестрирован!");
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        }
    }
}
