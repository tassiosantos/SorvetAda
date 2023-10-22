package com.ada.sorvetada.controllers;

import com.ada.sorvetada.dtos.ClientDto;
import com.ada.sorvetada.entities.Client;
import com.ada.sorvetada.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sorvetada/api/client")
public class ClientController {
    ClientService clientService;


    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping("/all")
    public List<Client> getClientes() {
        return clientService.getAll();
    }


    @PostMapping("")
    public ResponseEntity<ClientDto> saveCliente(@RequestBody ClientDto cliente) {
        try {
            ClientDto savedCliente = clientService.createNewClient(cliente);
            if (savedCliente != null)
                return new ResponseEntity<>(savedCliente, HttpStatus.CREATED);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClienteById(@PathVariable("id") Long idClient) {
        return new ResponseEntity<Client>(clientService.getById(idClient), HttpStatus.OK);
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Client>> getClienteByNome(@RequestParam("nome") String name) {
        return new ResponseEntity<Client>(clientService.getByName(name), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
