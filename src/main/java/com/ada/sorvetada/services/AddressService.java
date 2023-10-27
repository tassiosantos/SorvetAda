package com.ada.sorvetada.services;

import com.ada.sorvetada.dtos.AddressDto;
import com.ada.sorvetada.dtos.CustomerDto;
import com.ada.sorvetada.entities.Address;
import com.ada.sorvetada.entities.Customer;
import com.ada.sorvetada.feign.AddressFeign;
import com.ada.sorvetada.repositories.AddressRepository;
import com.ada.sorvetada.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@EnableFeignClients
public class AddressService {

    private final AddressFeign addressFeign;
    private final AddressRepository addressRepository;

    private final CustomerRepository customerRepository;

   public AddressDto requestCep(AddressDto cepAddress){
        return addressFeign.searchAddressCep(cepAddress.getCep());
   }


    public AddressDto saveAddress(AddressDto adressDto) {
       Optional<Customer> customerOpt = customerRepository.findById(1L);
       Customer customer = customerOpt.orElseThrow(() -> new RuntimeException("Does not exist client with this id"));
       Address address = new Address(adressDto.getId(),
               adressDto.getCep(),
               adressDto.getLogradouro(),
               adressDto.getBairro(),
               adressDto.getLocalidade(),
               adressDto.getNumeroCasa(),
               adressDto.getUf(),
               customer);

       Address savedAddress = addressRepository.save(address);
       return createAddressDto(savedAddress);
    }

    public AddressDto updateAddress(AddressDto adressDto) {
        Optional<Customer> customerOpt = customerRepository.findById(1L);
        Customer customer = customerOpt.orElseThrow(() -> new RuntimeException("Does not exist client with this id"));
        Address address = new Address(adressDto.getId(),
                adressDto.getCep(),
                adressDto.getLogradouro(),
                adressDto.getBairro(),
                adressDto.getLocalidade(),
                adressDto.getNumeroCasa(),
                adressDto.getUf(),
                customer);
        Address savedAddress = addressRepository.save(address);
        return createAddressDto(savedAddress);
    }





    private AddressDto createAddressDto(Address address){
       return new AddressDto().builder().id(address.getId())
               .cep(address.getCep())
               .bairro(address.getBairro())
               .localidade(address.getLocalidade())
               .logradouro(address.getLogradouro())
               .numeroCasa(address.getNumeroCasa())
               .uf(address.getUf())
               .customerId(address.getCustomer().getId())
               .build();
    }

    public List<AddressDto> getAll(CustomerDto customerDto) {
       List<Address> adresses = addressRepository.findByCustomer(customerRepository.findById(customerDto.getId()));

       return addressRepository.findByCustomer(customerRepository.findById(customerDto.getId())).stream()
               .map(address -> createAddressDto(address)).toList();
    }
}
