package com.ada.sorvetada.controllers;
import com.ada.sorvetada.dtos.AddressDto;
import com.ada.sorvetada.dtos.CustomerDto;
import com.ada.sorvetada.entities.Address;
import com.ada.sorvetada.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.ada.sorvetada.services.AddressService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/endereco")
public class AddressController {


    private final AddressService addressService;
    private final CustomerService customerService;

    @RequestMapping("/consulta")
    public ResponseEntity requestCep(@RequestBody AddressDto addressRequest){
        return ResponseEntity.ok(addressService.requestCep(addressRequest));
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AddressDto> saveAddress(@RequestBody AddressDto addressDto){
        try {
            System.out.println(addressDto.getLogradouro());
            AddressDto savedAddress = addressService.saveAddress(addressDto);
            if(savedAddress != null)
                return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AddressDto> editAddress(@RequestBody AddressDto addressDto){
        try {
            AddressDto savedAddress = addressService.updateAddress(addressDto);
            if(savedAddress != null)
                return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteAddress(@PathVariable Long id){
        try {
            boolean deletedAddress = addressService.deleteAddress(id);
            if(deletedAddress) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Address deleted successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @GetMapping("/getall")
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity<List<AddressDto>> getCustomerAddresses(@RequestBody CustomerDto customerDto){
        try {
            List<AddressDto> adresses = addressService.getAll(customerDto);
            if(!adresses.isEmpty())
                return new ResponseEntity<>(adresses, HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }









}
