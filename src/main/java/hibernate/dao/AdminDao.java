package hibernate.dao;

import hibernate.model.Admin;

import java.util.List;

public interface AdminDao {

    List<Admin> findAll();

    Admin getAdminById(int id);

    Admin getAdminByUsername(String username);

    boolean insertAdmin(Admin admin);

    boolean deleteAdmin(int id);
}
