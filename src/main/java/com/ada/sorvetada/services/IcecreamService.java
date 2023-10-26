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

    public boolean deleteIcecream (IcecreamDto icecreamDto){
        Optional<Icecream> optIcecream = icecreamRepository.findById(icecreamDto.getId());
        if(optIcecream.isPresent()){
            icecreamRepository.deleteById(icecreamDto.getId());
            return true;
        }else {
            throw new RuntimeException("Sorvete não encontrado com esse ID");
        }

    }

    public boolean updateUnitPriceById(IcecreamDto icecreamDto) {
        Optional<Icecream> optIcecream = icecreamRepository.findById(icecreamDto.getId());

        if (optIcecream.isPresent() && icecreamDto.getUnitPrice() != null) {
            icecreamRepository.updateUnitPrice(icecreamDto.getUnitPrice(), icecreamDto.getId());
            return true;
        }else {
            throw new RuntimeException("Sorvete não encontrado com esse ID");
        }
    }

    public boolean updateUrlPhotoById(IcecreamDto icecreamDto) {
        Optional<Icecream> optIcecream = icecreamRepository.findById(icecreamDto.getId());

        if (optIcecream.isPresent() && icecreamDto.getUrlPhoto() != null && icecreamDto.getUrlPhoto().isEmpty()) {
            icecreamRepository.updateUrlPhoto(icecreamDto.getUrlPhoto(), icecreamDto.getId());
            return true;
        }else {
            throw new RuntimeException("Sorvete não encontrado com esse ID");
        }
    }



}
