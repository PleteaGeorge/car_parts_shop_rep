package org.example.ui;

import lombok.Getter;
import org.example.config.ui.Command;
import org.example.config.ui.Functor;
import org.example.config.ui.UserConsoleConfiguration;
import org.hibernate.Session;

import java.util.Map;
import java.util.Scanner;

public class UserConsole {
  private static UserConsole instance;
  @Getter
  private final Scanner scanner;
  private final Map<Command, Functor> commands;
  private final Command exitCommand;
  private final String prompter;
  private final String unknownCommandFormat;

  private UserConsole() {
    scanner = new Scanner(System.in);
    commands = UserConsoleConfiguration.getCommands();
    exitCommand = UserConsoleConfiguration.getExitCommand();
    prompter = UserConsoleConfiguration.PROMPTER;
    unknownCommandFormat = UserConsoleConfiguration.UNKNOWN_COMMAND_FORMAT;
  }

  public static UserConsole get() {
    if (null != instance) {
      return instance;
    }
    synchronized (UserConsole.class) {
      if (null == instance) {
        instance = new UserConsole();
      }
    }
    return instance;
  }

  private void showPrompter() {
    System.out.print(prompter + " ");
  }

  private void showUnknownCommand(Command command) {
    System.out.printf(unknownCommandFormat + "\n", command.getCommand());
    showHelp();
  }

  public void showHelp() {
    commands.keySet().forEach(command -> System.out.println(" " + command.getCommand()));
  }

  public static void run(Session session) {
    get().execute(session);
  }

  public void execute(Session session) {
    while (true) {
      showPrompter();
      final Command command = new Command(scanner.nextLine().trim().replaceAll("\\s", ""));
      final Functor functor = commands.get(command);
      if (null == functor) {
        showUnknownCommand(command);
        continue;
      }
      functor.execute(session);
      if (exitCommand.equals(command)) {
        break;
      }
    }
  }
}
