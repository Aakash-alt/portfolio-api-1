package com.univ.backend.services;

import com.univ.backend.entities.Admin;
import com.univ.backend.exceptions.AdminNotFoundException;
import com.univ.backend.exceptions.IncorrectAdminDataException;
import com.univ.backend.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Admin> getAdminList(String token) {
        List<Admin> admins = adminRepository.findAll();
        return admins;
    }

    @Override
    public Admin verifyAdminRequest(Admin admin) throws IncorrectAdminDataException, AdminNotFoundException {
        Optional<Admin> adm = adminRepository.findByUserName(admin.getUserName());
        if(adm.isPresent()) {
            Admin mainAdmin = adm.get();
            if(mainAdmin.getUserName().equals(admin.getUserName()) && mainAdmin.getPassword().equals(admin.getPassword())) {
                return mainAdmin;
            } else {
                throw new IncorrectAdminDataException("The password is incorrect!", mainAdmin);
            }
        }
        throw new AdminNotFoundException("No admin found with the given username!", admin);
    }
}
