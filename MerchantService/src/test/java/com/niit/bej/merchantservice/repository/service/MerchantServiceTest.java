package com.niit.bej.merchantservice.repository.service;


import com.niit.bej.merchantservice.repository.MerchantRepository;
import com.niit.bej.merchantservice.service.MerchantServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MerchantServiceTest {

    @InjectMocks
    MerchantServiceImpl merchantService;
    @Mock
    private MerchantRepository merchantRepository;


}
