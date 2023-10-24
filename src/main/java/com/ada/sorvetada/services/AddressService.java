package com.ada.sorvetada.services;

import com.ada.sorvetada.entities.AddressRequest;
import com.ada.sorvetada.feign.AddressFeign;
import com.ada.sorvetada.feign.AddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableFeignClients
public class AddressService {

    private final AddressFeign addressFeign;
    public AddressResponse execute (AddressRequest request){
        return addressFeign.searchAddressCep((request.getCep()));
    }
}
