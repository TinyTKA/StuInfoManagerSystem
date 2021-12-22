package xyz.taouvw.Mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xyz.taouvw.pojo.User;

import java.util.List;

public interface UserMapper {
    List<User> selectAll();

    User selectByid(String code);

    void addStu(User user);

    void deleteById(String code);

    void UpdateStuInfo(User user);

    // 通过学号来完成批量删除
    void deleteByCodes(@Param("codes") String[] codes);

    /**
     * 分页查询
     *
     * @param begin
     * @param size
     * @return
     */
    @Select("select * from stu limit #{begin} , #{size}")
    List<User> selectByPage(@Param("begin") int begin, @Param("size") int size);

    /**
     * 查询总记录数
     *
     * @return
     */
    @Select("select count(*) from stu")
    int selectTotalCount();

    User LoginSelectByid(@Param("code") String code, @Param("passwd") String passwd);

    void UpdatePasswd(@Param("code") String code,@Param("passwd") String passwd);
}
