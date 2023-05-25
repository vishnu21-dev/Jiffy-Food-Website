package com.niit.bej.orderservice.proxy;

import com.niit.bej.orderservice.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "authService", url = "localhost:8084")
public interface UserProxy {

    @PostMapping("/userAuth/register")
    public ResponseEntity<?> registerUserProxy(User user);
}
