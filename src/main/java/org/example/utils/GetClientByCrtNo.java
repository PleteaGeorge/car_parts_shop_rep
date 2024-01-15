package org.example.utils;

import org.example.repository.ClientRepository;
import org.example.repository.entities.Client;
import org.example.ui.UserConsole;
import org.hibernate.Session;

import java.util.HashMap;
import java.util.Map;

public class GetClientByCrtNo {
  public static Client execute(Session session) {
    Map<Integer, Client> map = new HashMap<>();
    int i = 0;
    for (Client client : new ClientRepository(session).findAll()) {
      map.put(++i, client);
      System.out.printf("%5d. %s\n", i, client.toString());
    }
    System.out.print("Choose client by current number: ");
    return map.get(Integer.parseInt(UserConsole.get().getScanner().nextLine()));
  }
}
