package com.ada.sorvetada.services;

import com.ada.sorvetada.dtos.ClientDto;
import com.ada.sorvetada.entities.Client;
import com.ada.sorvetada.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }


    public List<Client> getAll() {
        return null;
    }


    public Object getById(Long id) {
        Optional<Client> optCliente = clientRepository.findById(id);
        Client cliente = optCliente.orElseThrow(() -> new RuntimeException("Não existe cliente com esse id"));
        return cliente;
    }

    public ClientDto createNewClient(ClientDto cliente) {
        return cliente;
    }

    public Object getByName(String name) {
        return null;
    }

    public void deleteClient(Long id) {

    }
}
