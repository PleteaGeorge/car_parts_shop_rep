package org.example.service;

import org.example.repository.CarPartRepository;
import org.example.repository.ClientRepository;
import org.example.repository.OfferRepository;
import org.example.repository.entities.Car;
import org.example.repository.entities.CarPart;
import org.example.repository.entities.Client;
import org.example.repository.entities.Offer;
import org.example.ui.UserConsole;
import org.example.utils.OfferUtility;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MakeOffer {
    //  1. Create offer
    //  2. Chose client
    //  3. Chose which pieces to sell
    //  4. Chose offer amount
    //  5. Persist offer
    public static void execute(Session session) {
        Offer offer = new Offer();
        while (true) {

            ClientRepository clientRepository = new ClientRepository(session);
            List<Client> clientList = clientRepository.findAll();
            Map<Integer, Client> clientHashMap = new HashMap<>();
            int i = 0;
            for (Client client : clientList) {
                clientHashMap.put(++i, client);
                System.out.printf("%5d. %s\n", i, client.toString());
            }
            System.out.print("Choose client by current number: ");
            Client client = clientHashMap.get(Integer.parseInt(UserConsole.get().getScanner().nextLine()));

            CarPartRepository carPartRepository = new CarPartRepository(session);
            List<CarPart> carParts = carPartRepository.findAll();
            Map<Integer, CarPart> carPartHashMap = new HashMap<>();
            i = 0;
            for (CarPart carPart : carParts) {
                carPartHashMap.put(++i, carPart);
                System.out.printf("%5d. %s\n", i, carPart.toString());
            }
            System.out.print("Choose car parts by ',' separated list of current numbers: ");
            List<CarPart> carPartList = Arrays.stream(UserConsole.get().getScanner().nextLine()
                            .replaceAll("\\s", "").split(","))
                    .map(Integer::parseInt).map(carPartHashMap::get).toList();
            Double totalAmount = carPartList.stream().map(CarPart::getPrice).reduce(.0, Double::sum);
            System.out.printf("Total amount: $%.2f\n", totalAmount);

            System.out.print("Set the offer by amount or by discount ? ( $ / [%] ): ");
            boolean byAmount = UserConsole.get().getScanner().nextLine().trim().equals("$");
            System.out.print("Set " + (byAmount ? "amount ( $ )" : "discount ( % )") + ": ");
            Double tmp = Double.parseDouble(UserConsole.get().getScanner().nextLine());
            if (!byAmount) {
                tmp = totalAmount * (1 - (tmp / 100));
            }
            offer.setAmount(tmp);
            offer.getCarParts().addAll(carPartList);

            System.out.println("Client: " + client);
            System.out.println(offer);
// choose to submit, modify or abort
            System.out.print("Submit, Modify, Abort ? ( S / [M] / A ): ");
            String answer = UserConsole.get().getScanner().nextLine().trim();
            if ("A".equalsIgnoreCase(answer)) {
                break;
            }
            if ("S".equalsIgnoreCase(answer)) {
                new OfferRepository(session).insert(offer, client);
                break;
            }
            offer.getCarParts().clear();
        }

    }
}
