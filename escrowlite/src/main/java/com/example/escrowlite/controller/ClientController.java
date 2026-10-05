package com.example.escrowlite.controller;

import com.example.escrowlite.entity.Client;
import com.example.escrowlite.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id,
                               @RequestBody Client client) {

        Client existingClient = clientService.getClientById(id);

        if (existingClient == null) {
            return null;
        }

        existingClient.setName(client.getName());
        existingClient.setEmail(client.getEmail());

        return clientService.saveClient(existingClient);
    }

    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable Long id) {

        Client existingClient = clientService.getClientById(id);

        if (existingClient == null) {
            return "Client not found";
        }

        clientService.deleteClient(id);
        return "Client deleted successfully";
    }
}