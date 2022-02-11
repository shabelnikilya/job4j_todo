package ru.job4j.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.model.Item;
import ru.job4j.model.User;
import ru.job4j.serializer.SerializerLocalDateTime;
import ru.job4j.store.HbmStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class AddItemServlet extends HttpServlet {
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new SerializerLocalDateTime())
            .create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String desc = req.getParameter("description");
        User user = (User) req.getSession().getAttribute("user");
        Item item = new Item(desc, LocalDateTime.now(), false, user);
        HbmStore.instance().addItem(item);

        String json = GSON.toJson(item);
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
