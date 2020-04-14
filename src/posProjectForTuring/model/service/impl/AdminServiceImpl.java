/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posProjectForTuring.model.service.impl;

import java.util.List;
import posProjectForTuring.model.Admin;
import posProjectForTuring.model.dao.AdminDao;
import posProjectForTuring.model.service.AdminService;

/**
 *
 * @author hp
 */
public class AdminServiceImpl implements AdminService {
    AdminDao adminDao = new AdminDao();
    
    @Override
    public void insertAdmin(Admin admin) {
        adminDao.insertAdmin(admin);
    }

    @Override
    public List<Admin> getAllAdmin() {
        return adminDao.getAllAdmin();
    }

    @Override
    public Admin getAdminByUsername(String username) {
        return adminDao.getAdminByUsername(username);
    }
    
}
