package org.example.config.ui;

import org.example.repository.CarPartNameRepository;
import org.example.repository.entities.CarPartName;

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
    result.put(getExitCommand(),
      session -> System.out.println("Exiting...")
    );
    // <description>
    result.put(new Command("newName1"),
      session -> new CarPartNameRepository(session).insert(new CarPartName("inside mirror"))
    );
    // <description>
    result.put(new Command("newName2"),
      session -> new CarPartNameRepository(session).insert(new CarPartName("driver left mirror"))
    );
    // <description>
    result.put(new Command("delName"),
      session -> {
        CarPartName carPartName = new CarPartName("driver left seat");
        CarPartNameRepository carPartNameRepository = new CarPartNameRepository(session);
        carPartNameRepository.insert(carPartName);
        carPartNameRepository.delete(carPartName.getId());
      }
    );
    // <description>
    result.put(new Command("update"),
      session -> {
        CarPartName carPartName = new CarPartName("pasenger right mirror");
        CarPartNameRepository carPartNameRepository = new CarPartNameRepository(session);
        carPartNameRepository.insert(carPartName);
        carPartNameRepository.update(carPartName.getId(), "passenger right mirror");
      }
    );
    return result;
  }
}
