package com.ada.sorvetada.controllers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ada.sorvetada.entities.AddressRequest;
import com.ada.sorvetada.services.AddressService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/endereco")
public class AddressController {

    private final AddressService addressService;
    @RequestMapping("/consulta")
    public ResponseEntity requestCep(@RequestBody AddressRequest addressRequest){
        return ResponseEntity.ok(addressService.execute(addressRequest));
    }
}
