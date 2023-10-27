package com.ada.sorvetada.services;

import com.ada.sorvetada.dtos.IcecreamDto;
import com.ada.sorvetada.entities.Icecream;
import com.ada.sorvetada.repositories.IcecreamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class IcecreamService {
    private IcecreamRepository icecreamRepository;

    public List<Icecream> getAll (){
        return  icecreamRepository.findAll();
    }
    public IcecreamDto createNewIcecream (Icecream icecream){
        return IcecreamDto.builder().id(icecream.getId())
                .name(icecream.getName())
                .unitPrice(icecream.getUnitPrice())
                .availableUnits(icecream.getAvailableUnits())
                .urlPhoto(icecream.getUrlPhoto())
                .build();
    }

    public IcecreamDto saveIcecream(IcecreamDto icecreamDto){
        Icecream icecream = new Icecream(icecreamDto.getName(), icecreamDto.getUnitPrice(),
                icecreamDto.getAvailableUnits(), icecreamDto.getUrlPhoto());

        Icecream savedIcecream = icecreamRepository.save(icecream);
        return createNewIcecream(savedIcecream);

    }

    public boolean deleteIcecream (Long id){
        Optional<Icecream> optIcecream = icecreamRepository.findById(id);
        if(optIcecream.isPresent()){
            icecreamRepository.deleteById(id);
            return true;
        }else {
            throw new RuntimeException("Sorvete não encontrado com esse ID");
        }

    }

    public boolean updateUnitPriceById(IcecreamDto icecreamDto) {
        Optional<Icecream> optIcecream = icecreamRepository.findById(icecreamDto.getId());
        Icecream icecream = optIcecream.orElseThrow(() -> new RuntimeException("Doesn't exist icecream by id"));

        icecream.setUnitPrice(icecreamDto.getUnitPrice());
        Icecream updatedIcecream = icecreamRepository.save(icecream);
        createNewIcecream(updatedIcecream);
        return true;
    }


    public boolean updateUrlPhotoById(IcecreamDto icecreamDto) {

        Optional<Icecream> optIcecream = icecreamRepository.findById(icecreamDto.getId());
        Icecream icecream = optIcecream.orElseThrow(() -> new RuntimeException("Doesn't exist icecream by id"));

        if (!icecream.getUrlPhoto().equals(icecreamDto.getUrlPhoto())) {
            icecream.setUrlPhoto(icecreamDto.getUrlPhoto());
            Icecream updatedIcecream = icecreamRepository.save(icecream);
            createNewIcecream(updatedIcecream);
        }
        return true;
    }




}
