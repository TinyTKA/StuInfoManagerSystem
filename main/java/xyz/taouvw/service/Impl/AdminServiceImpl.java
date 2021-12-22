package xyz.taouvw.service.Impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import xyz.taouvw.Mapper.AdminMapper;
import xyz.taouvw.pojo.Admin;
import xyz.taouvw.utils.SqlSessionFactoryUtils;

public class AdminServiceImpl implements AdminService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public Admin searchAdmin(Admin admin) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        Admin admin1 = mapper.searchAdmin(admin);
        sqlSession.close();
        return admin1;
    }

    @Override
    public void addAdmin(Admin admin) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        mapper.addAdmin(admin);
        sqlSession.commit();
        sqlSession.close();
    }
}
