package com.niit.bej.service;

import com.niit.bej.domain.Merchant;
import com.niit.bej.exception.AdminDoesNotExistException;
import com.niit.bej.exception.MerchantAlreadyExistException;

import java.util.List;

public interface AdminService {
    Merchant addMerchant(Merchant merchant) throws MerchantAlreadyExistException;

    List<Merchant> getMerchant(String userId, String password) throws AdminDoesNotExistException;
}
