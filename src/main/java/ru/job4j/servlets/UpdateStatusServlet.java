package ru.job4j.servlets;

import ru.job4j.store.HbmStore;
import ru.job4j.store.Store;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateStatusServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        boolean done = Boolean.parseBoolean(req.getParameter("done"));
        Store store = HbmStore.instance();
        store.changeStatusAndUpdate(id, done);

    }
}
