package com.ada.sorvetada.controllers;

import com.ada.sorvetada.dtos.IcecreamDto;
import com.ada.sorvetada.entities.Icecream;
import com.ada.sorvetada.services.IcecreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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




}
