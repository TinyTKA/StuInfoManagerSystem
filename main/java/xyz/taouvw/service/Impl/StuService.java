package xyz.taouvw.service.Impl;

import xyz.taouvw.pojo.PageBean;
import xyz.taouvw.pojo.User;

import java.util.List;

public interface StuService {
    /**
     * 查询所有
     * @return
     */
    List<User> selectAll();
    void addStu(User user);
    void deleteById(String code);
    void UpdateStuInfo(User user);
    User selectByid(String code);

    void deleteByCodes(String[] codes);

    PageBean<User> selectByPage(int currentPage, int pageSize);
    int selectTotalCount();

    User LoginSelectByid(String code,String passwd);

    void UpdatePasswd(String code,String passwd);
}
