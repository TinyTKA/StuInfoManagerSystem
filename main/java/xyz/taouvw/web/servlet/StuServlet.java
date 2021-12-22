package xyz.taouvw.web.servlet;


import com.alibaba.fastjson.JSON;
import xyz.taouvw.pojo.Admin;
import xyz.taouvw.pojo.PageBean;
import xyz.taouvw.pojo.User;
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
import java.util.List;


@WebServlet("/stu/*")
public class StuServlet extends BaseServlet {

    private final StuService stuService = new UserServiceImpl();

    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = stuService.selectAll();
        String jsonString = JSON.toJSONString(users);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        User user = JSON.parseObject(s, User.class);
        user.setDate(user.getDate().split("T")[0]);
        stuService.addStu(user);
        resp.getWriter().write("success");
    }

    public void deleteById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        String s = reader.readLine();

        stuService.deleteById(s);
    }

    public void UpdateStuInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        User user = JSON.parseObject(s, User.class);
        stuService.UpdateStuInfo(user);
        resp.getWriter().write("success");
    }

    public void selectByid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        User user = stuService.selectByid(s);
        String s1 = JSON.toJSONString(user);
        if (s1.equals("null")) {
            System.out.println(s1);
            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write("error");
        } else {
            System.out.println("[" + s1 + "]");
            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write("[" + s1 + "]");
        }
    }

    // 批量删除
    public void deleteByCodes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        String[] strings = JSON.parseObject(s, String[].class);
        stuService.deleteByCodes(strings);
        resp.getWriter().write("success");
    }

    // 分页查询
    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("PageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        PageBean<User> pageBean = stuService.selectByPage(currentPage, pageSize);

        Cookie[] cookies = req.getCookies();
        for (Cookie cookie: cookies) {
            if(cookie.getName().equals("username")){
               if(cookie.getValue()==null){
                   pageBean.setUsername("null");
               }else{
                   pageBean.setUsername(cookie.getValue());
               }
            }
        }

        String pagebean = JSON.toJSONString(pageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(pagebean);
    }

    public void UpdatePasswd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        Admin admin = JSON.parseObject(s, Admin.class);
        stuService.UpdatePasswd(admin.getCode(),admin.getPasswd());
        resp.getWriter().write("success");
    }

}
