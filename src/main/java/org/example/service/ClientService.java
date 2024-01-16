package org.example.service;

import org.example.repository.entities.Client;
import org.example.utils.GetClientByCrtNo;
import org.hibernate.Session;

public class ClientService {
  private final Session session;
  public ClientService(Session session){
    this.session=session;
  }

  public void showClientOffers() {
    // choose the client (show clients) by crt.no.
    Client client = GetClientByCrtNo.execute(session);
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
}