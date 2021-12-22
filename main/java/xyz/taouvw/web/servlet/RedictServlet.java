package xyz.taouvw.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/redirectServlet")
public class RedictServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        Cookie[] cookies = req.getCookies();
        for (Cookie c: cookies) {
            if(c.getName().equals("type")){
                if(c.getValue().equals("admin")){
                    resp.getWriter().write("admin");
                }else if(c.getValue().equals("user")){
                    resp.getWriter().write("user");
                }else{
                    resp.getWriter().write("nothing");
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
