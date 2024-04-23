package org.example.utils.populate_database;

import org.example.repository.ClientRepository;
import org.example.repository.entities.Client;
import org.hibernate.Session;

public class ClientsUtility {
  public static void deleteExisting(Session session) {
    ClientRepository clientRepository = new ClientRepository(session);
    clientRepository.findAll().stream().map(Client::getId).forEach(clientRepository::delete);
  }

  public static void populate(Session session) {
    ClientRepository clientRepository = new ClientRepository(session);
    clientRepository.insert(new Client("John Doe", "0555 555 555"));
    clientRepository.insert(new Client("Jane Doe", "0555 555 444"));
    clientRepository.insert(new Client("John Deere", "0555 555 333"));
    clientRepository.insert(new Client("Jane Deere", "0555 555 222"));
    clientRepository.insert(new Client("John Gold", "0555 555 111"));
    clientRepository.insert(new Client("Jane Gold", "0555 555 000"));
    clientRepository.insert(new Client("Johnny Walker", "0555 555 999"));
    clientRepository.insert(new Client("Jenny Walker", "0555 555 888"));
    clientRepository.insert(new Client("Johnny Fairy", "0555 555 777"));
    clientRepository.insert(new Client("Jenny Fairy", "0555 555 666"));
  }
}
