package org.example.config.ui;

import org.example.service.MakeOffer;
import org.example.service.ShowClientOffers;
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

  public static Map<Command, Functor> getCommands() {
    final Map<Command, Functor> result = new HashMap<>();
    // EXIT_COMMAND
    result.put(getExitCommand(), session -> System.out.println("Exiting..."));
    // builds an offer for an existing client with existing parts establishing an amount or a discount
    result.put(new Command("makeOffer"), MakeOffer::execute);
    // self-explanatory
    result.put(new Command("showClientOffers"), ShowClientOffers::execute);
    // self-explanatory
    result.put(new Command("help"), session -> UserConsole.get().showHelp());
    //
    // aliases
    result.put(new Command("O"), result.get(new Command("makeOffer")));
    result.put(new Command("sC"), result.get(new Command("showClientOffers")));
    //
    return result;
  }
}
