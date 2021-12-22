package xyz.taouvw.Mapper;

import xyz.taouvw.pojo.Admin;

public interface AdminMapper {
    Admin searchAdmin(Admin admin);
    void addAdmin(Admin admin);
}
