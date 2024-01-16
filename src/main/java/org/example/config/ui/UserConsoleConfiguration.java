package org.example.config.ui;

import org.example.service.OfferService;
import org.example.service.ClientService;
import org.example.service.Test;
import org.example.ui.PassToBackend;
import org.example.ui.UserConsole;

import java.util.HashMap;
import java.util.Map;

public class UserConsoleConfiguration {
  private final static String EXIT_COMMAND = "exit";
  public final static String PROMPTER = " Car Parts Store \ud83d\udE02 >";
  public final static String UNKNOWN_COMMAND_FORMAT = "unknown command '%s'; check the list";
  private static Command exitCommand;

  public static Command getExitCommand() {
    if (null != exitCommand) {
      return exitCommand;
    }
    synchronized (UserConsoleConfiguration.class) {
      if (null == exitCommand) {
        exitCommand = new Command(EXIT_COMMAND);
      }
    }
    return exitCommand;
  }

  public static Map<Command, PassToBackend> getCommands() {
    final Map<Command, PassToBackend> result = new HashMap<>();
    // EXIT_COMMAND
    result.put(getExitCommand(), session -> System.out.println("Exiting..."));
    // builds an offer for an existing client with existing parts establishing an amount or a discount
    result.put(new Command("makeOffer"), session -> new OfferService(session).makeOffer());
    // self-explanatory
    result.put(new Command("showClientOffers"), session -> new ClientService(session).showClientOffers());
    // self-explanatory
    result.put(new Command("help"), session -> UserConsole.getInstance().showHelp());
    // self-explanatory
    result.put(new Command("test"), Test::execute);
    //
    // aliases
    result.put(new Command("O"), result.get(new Command("makeOffer")));
    result.put(new Command("sC"), result.get(new Command("showClientOffers")));
    //
    return result;
  }
}
