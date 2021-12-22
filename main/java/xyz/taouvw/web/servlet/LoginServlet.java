package xyz.taouvw.web.servlet;

import com.alibaba.fastjson.JSON;
import xyz.taouvw.pojo.Admin;
import xyz.taouvw.pojo.User;
import xyz.taouvw.service.Impl.AdminService;
import xyz.taouvw.service.Impl.AdminServiceImpl;
import xyz.taouvw.service.Impl.StuService;
import xyz.taouvw.service.Impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/login/*")
public class LoginServlet extends BaseServlet {

    private final AdminService adminService = new AdminServiceImpl();
    private final StuService stuService = new UserServiceImpl();

    public void searchAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        //System.out.println(s);
        Admin admin = JSON.parseObject(s, Admin.class);
        Admin admin1 = adminService.searchAdmin(admin);
        resp.setContentType("text/json;charset=utf-8");
         // 管理员库中没有查到信息
        if (admin1 == null) {
            User user = stuService.LoginSelectByid(admin.getCode(), admin.getPasswd());
            if (user == null) {
                resp.getWriter().write("error");
            } else {
                Cookie cookie = new Cookie("username",user.getName());
                Cookie cookie2 = new Cookie("code",user.getCode());
                Cookie cookie3 = new Cookie("type","user");
                cookie.setPath("/");
                cookie2.setPath("/");
                cookie3.setPath("/");
                cookie.setMaxAge(60*60*24*1);
                cookie2.setMaxAge(60*60*24*1);
                cookie3.setMaxAge(60*60*24*1);
                resp.addCookie(cookie);
                resp.addCookie(cookie2);
                resp.addCookie(cookie3);
                resp.getWriter().write("success");
            }
        } else {
            Cookie cookie = new Cookie("username",admin1.getName());
            Cookie cookie2 = new Cookie("type","admin");
            cookie.setPath("/");
            cookie2.setPath("/");
            resp.addCookie(cookie);
            resp.addCookie(cookie2);
            resp.getWriter().write("success");
        }

    }

    public void addAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        System.out.println(s);
        Admin admin = JSON.parseObject(s, Admin.class);
        adminService.addAdmin(admin);
        resp.getWriter().write("success");
    }

    public void getCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie c: cookies) {
            if(c.getName().equals("code")){
                resp.getWriter().write(c.getValue());
            }
        }
    }



}
