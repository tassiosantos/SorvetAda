package com.ada.sorvetada.controllers;

import com.ada.sorvetada.dtos.CustomerDto;
import com.ada.sorvetada.dtos.IcecreamDto;
import com.ada.sorvetada.entities.Icecream;
import com.ada.sorvetada.services.IcecreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("sorvetada/api/icecream")
public class IcecreamController {
    private final IcecreamService icecreamService;

    @PostMapping("/create")
    public IcecreamDto createIcecream(@RequestBody IcecreamDto icecreamDto){
        return icecreamService.saveIcecream(icecreamDto);
    }
    @GetMapping("/all")
    public List<Icecream> getAllIcecreams(){
        return  icecreamService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteIcecream(@PathVariable Long id) {
        boolean deleted = icecreamService.deleteIcecream(id);
        if (deleted) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Icecream deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PutMapping("/updateUnitPrice")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Map<String, String>> updateUnitPriceById(@RequestBody IcecreamDto icecreamDto) {
        try {
            boolean updatedPrice = icecreamService.updateUnitPriceById(icecreamDto);
            if (updatedPrice) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Photo updated successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/updateUrlPhoto")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Map<String, String>> updateUrlPhotoById(@RequestBody IcecreamDto icecreamDto) {
        try {
            boolean updatedPhoto = icecreamService.updateUrlPhotoById(icecreamDto);
            if (updatedPhoto) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Photo updated successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}
