package xyz.taouvw.service.Impl;

import xyz.taouvw.pojo.Admin;

public interface AdminService {
    Admin searchAdmin(Admin admin);

    void addAdmin(Admin admin);
}
