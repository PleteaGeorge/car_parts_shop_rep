package org.example.service;

import org.example.repository.ClientRepository;
import org.example.repository.entities.Client;
import org.example.ui.UserConsole;
import org.hibernate.Session;

import java.util.HashMap;
import java.util.Map;

public class ClientService {
  private final ClientRepository clientRepository;
  private final Session session;

  public ClientService(Session session) {
    this.session = session;
    clientRepository = new ClientRepository(session);
  }

  public void showClientOffers() {
    // choose the client (show clients) by crt.no.
    Client client = getClientByCrtNo();
    // show offers one by one
    System.out.print("======   Client [" + client + "] ");
    if (client.getOffers().isEmpty()) {
      System.out.println("has no offer.");
      return;
    }
    System.out.println("offer(s):");
    client.getOffers().forEach(System.out::println);
    System.out.println("======   end [" + client.getName() + "] offer(s)");
  }

  public Client getClientByCrtNo() {
    Map<Integer, Client> map = new HashMap<>();
    int i = 0;
    for (Client client : clientRepository.findAll()) {
      map.put(++i, client);
      System.out.printf("%5d. %s\n", i, client.toString());
    }
    System.out.print("Choose client by current number: ");
    return map.get(Integer.parseInt(UserConsole.getInstance().getScanner().nextLine()));
  }
}
