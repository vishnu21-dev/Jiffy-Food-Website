package com.niit.bej.domain;

import java.util.List;
import java.util.Objects;

public class Admin {
    private String userId;

    private String password;
    private List<Merchant> merchantList;

    public Admin(String userId, String password, List<Merchant> merchantList) {
        this.userId = userId;
        this.password = password;
        this.merchantList = merchantList;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", merchantList=" + merchantList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(userId, admin.userId) && Objects.equals(password, admin.password) && Objects.equals(merchantList, admin.merchantList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, password, merchantList);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Merchant> getMerchantList() {
        return merchantList;
    }

    public void setMerchantList(List<Merchant> merchantList) {
        this.merchantList = merchantList;
    }
}
