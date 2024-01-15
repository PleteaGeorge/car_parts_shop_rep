package org.example.repository;

import org.example.repository.entities.Car;
import org.example.repository.entities.Client;
import org.hibernate.Session;

public class ClientRepository extends Repository<Client> {

    public ClientRepository(Session session){
        super(session, Client.class);
    }
}