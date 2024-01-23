package org.example.service;

import org.example.repository.ClientRepository;
import org.example.repository.entities.Client;
import org.example.ui.UserConsole;
import org.hibernate.Session;

import java.util.HashMap;
import java.util.List;
package org.example.service;

import org.example.repository.ClientRepository;
import org.example.repository.entities.Client;
import org.example.ui.UserConsole;
import org.hibernate.Session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowClientOffers {
  //  1. Show  clients and chose one
  //  2. Show offers one by one
  public static void execute(Session session) {
    ClientRepository clientRepository = new ClientRepository(session);
    List<Client> clientList = clientRepository.findAll();
    Map<Integer, Client> clientHashMap = new HashMap<>();
    int i = 0;
    for (Client client : clientList) {
      clientHashMap.put(++i, client);
      System.out.printf("%5d. %s\n", i, client.toString());
    }
    System.out.print("Choose client by current number: ");
    Client  client =  clientHashMap.get(Integer.parseInt(UserConsole.get().getScanner().nextLine()));

    System.out.print("======   Client [" + client + "] ");
    if (client.getOffers().isEmpty()) {
      System.out.println("has no offer.");
      return;
    }
    System.out.println("offer(s):");
    client.getOffers().forEach(System.out::println);
    System.out.println("======   end [" + client.getName() + "] offer(s)");
  }
}