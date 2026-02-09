package com.kagrawal.crudapp.web;

import com.kagrawal.crudapp.Exception.DAOException;
import com.kagrawal.crudapp.dao.UserDAOImpl;
import com.kagrawal.crudapp.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/api/auth/*")
public class AuthServlet extends HttpServlet {

    private UserDAOImpl userDAO;

    @Override
    public void init() {
        userDAO = new UserDAOImpl();
    }

    /* ===================== POST ===================== */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");
        String path = req.getPathInfo();

        try {
            if (path == null || path.equals("/")) {
                path = "/login";
            }

            switch (path) {
                case "/register":
                    register(readJson(req), resp);
                    break;

                case "/logout":
                    logout(req, resp);
                    break;

                default:
                    login(readJson(req), req, resp);
            }

        } catch (DAOException e) {
            send(resp, 500, e.getMessage());
        }
    }

    /* ===================== GET ===================== */
    /* Used by header.html to fetch logged-in user */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            resp.setStatus(401);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"error\":\"Unauthorized\"}");
            return;
        }

        User user = (User) session.getAttribute("user");

        resp.setContentType("application/json");
        resp.getWriter().write(
                "{"
                        + "\"username\":\"" + user.getUsername() + "\","
                        + "\"name\":\"" + user.getName() + "\""
                        + "}"
        );
    }

    /* ===================== AUTH METHODS ===================== */

    private void login(Map<String, String> body,
                       HttpServletRequest req,
                       HttpServletResponse resp) throws IOException {

        String username = body.get("username");
        String password = body.get("password");

        if (username == null || password == null) {
            send(resp, 400, "Missing credentials");
            return;
        }

        User user = userDAO.insert(username, password);

        if (user == null) {
            send(resp, 401, "Invalid username or password");
            return;
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("user", user);

        resp.getWriter().write("{\"message\":\"Login successful\"}");
    }

    private void register(Map<String, String> body,
                          HttpServletResponse resp) throws IOException {

        String name = body.get("name");
        String mobile = body.get("mobile");
        String username = body.get("username");
        String password = body.get("password");

        if (name == null || mobile == null || username == null || password == null) {
            send(resp, 400, "Missing fields");
            return;
        }

        if (userDAO.exists(username)) {
            send(resp, 409, "Username already exists");
            return;
        }

        userDAO.register(new User(name, mobile, username, password));

        resp.setStatus(201);
        resp.getWriter().write("{\"message\":\"User registered successfully\"}");
    }

    private void logout(HttpServletRequest req,
                        HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        resp.getWriter().write("{\"message\":\"Logout successful\"}");
    }

    /* ===================== HELPERS ===================== */

    private Map<String, String> readJson(HttpServletRequest req) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = req.getReader();
        String line;
        while ((line = br.readLine()) != null) sb.append(line);

        String json = sb.toString()
                .replace("{", "")
                .replace("}", "")
                .replace("\"", "");

        Map<String, String> map = new HashMap<>();
        for (String pair : json.split(",")) {
            String[] kv = pair.split(":");
            if (kv.length == 2) {
                map.put(kv[0].trim(), kv[1].trim());
            }
        }
        return map;
    }

    private void send(HttpServletResponse resp, int code, String msg)
            throws IOException {
        resp.setStatus(code);
        resp.getWriter().write("{\"error\":\"" + msg + "\"}");
    }
}
