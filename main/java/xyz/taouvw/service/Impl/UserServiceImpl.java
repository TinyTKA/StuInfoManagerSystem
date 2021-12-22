package xyz.taouvw.service.Impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import xyz.taouvw.Mapper.UserMapper;
import xyz.taouvw.pojo.PageBean;
import xyz.taouvw.pojo.User;
import xyz.taouvw.utils.SqlSessionFactoryUtils;

import java.util.List;

public class UserServiceImpl implements StuService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<User> selectAll() {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> users = userMapper.selectAll();

        sqlSession.close();

        return users;


    }

    @Override
    public void addStu(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.addStu(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteById(String code) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteById(code);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void UpdateStuInfo(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.UpdateStuInfo(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public User selectByid(String code) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectByid(code);
        sqlSession.close();
        return user;
    }

    @Override
    public void deleteByCodes(String[] codes) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteByCodes(codes);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public PageBean<User> selectByPage(int currentPage, int pageSize) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        int begin = (currentPage - 1) * pageSize;

        List<User> rows = mapper.selectByPage(begin, pageSize);
        int totalCount = mapper.selectTotalCount();
        PageBean<User> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        sqlSession.close();
        return pageBean;
    }

    @Override
    public int selectTotalCount() {
        return 0;
    }

    @Override
    public User LoginSelectByid(String code, String passwd) {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //System.out.println(code + "ssssss" + passwd);
        User user = mapper.LoginSelectByid(code, passwd);
        sqlSession.close();
        return user;
    }

    @Override
    public void UpdatePasswd(String code, String passwd) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.UpdatePasswd(code,passwd);
        sqlSession.commit();
        sqlSession.close();
    }
}
