package com.niit.bej.controller;

import com.niit.bej.exception.AdminDoesNotExistException;
import com.niit.bej.exception.MerchantAlreadyExistException;
import com.niit.bej.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/registerMerchantInAdmin")
    public ResponseEntity<?> register(@RequestBody Merchant merchant) {
        try {
            Merchant registerMerchant = adminService.addMerchant(merchant);
            return new ResponseEntity<>(registerMerchant, HttpStatus.CREATED);
        } catch (MerchantAlreadyExistException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getMerchant/{userId}/{password}")
    public ResponseEntity<?> getMerchant(@PathVariable String userId, @PathVariable String password) {

        try {
            List<Merchant> merchantList = adminService.getMerchant(userId, password);
            return new ResponseEntity<>(merchantList, HttpStatus.OK);
        } catch (AdminDoesNotExistException e) {
            throw new RuntimeException(e);
        }
    }
}
