package org.example.service;

import org.example.repository.ClientRepository;
import org.example.repository.OfferRepository;
import org.example.repository.entities.Client;
import org.example.ui.UserConsole;

import java.util.HashMap;
import java.util.Map;

public class ClientService {
  private final ClientRepository clientRepository;

  public ClientService(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
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
    client.getOffers().forEach(offer ->
      System.out.println(new OfferService(new OfferRepository(clientRepository.getSession()))
        .getPrinted(offer, 2))
    );
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
