package com.niit.bej.orderservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "authService", url = "localhost:8084")
public interface UserProxy {
}
