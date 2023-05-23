package com.niit.bej.merchantservice.proxy;

import com.niit.bej.merchantservice.domain.Merchant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "authService", url = "localhost:8084")
public interface MerchantProxy {

    @PostMapping("/userAuth/register")
    public ResponseEntity<?> registerProxy(Merchant merchant);
}
