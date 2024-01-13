package org.example.service;

import org.example.repository.entities.Client;
import org.example.utils.GetClientByCrtNo;
import org.hibernate.Session;

public class ShowClientOffers {
  public static void execute(Session session) {
    // choose the client (show clients) by crt.no.
    Client client = GetClientByCrtNo.execute(session);
    // show offers one by one
    System.out.print("Client [" + client + "] ");
    if (client.getOffers().isEmpty()) {
      System.out.println("has no offer.");
      return;
    }
    System.out.println("offer(s):");
    client.getOffers().forEach(System.out::println);
  }
}
