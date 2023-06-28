package com.niit.bej.service;

import com.niit.bej.domain.Merchant;
import com.niit.bej.exception.AdminDoesNotExistException;
import com.niit.bej.exception.MerchantAlreadyExistException;
import com.niit.bej.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;

    @Override
    public Merchant addMerchant(Merchant merchant) throws MerchantAlreadyExistException {
        if (adminRepository.findById(merchant.getEmailId()).isPresent()) {
            throw new MerchantAlreadyExistException("Merchant Already Exist ");
        } else
            adminRepository.save(merchant);
        return merchant;
    }

    @Override
    public List<Merchant> getMerchant(String userId, String password) throws AdminDoesNotExistException {
        if (userId.equals("jiffyfoodapp@gmail.com")) {
            if (password.equals("jiffyfood123")) {
                return adminRepository.findAll();
            }
            throw new AdminDoesNotExistException("Check your credentials");

        }
        throw new AdminDoesNotExistException("Check your credentials");
    }
}
