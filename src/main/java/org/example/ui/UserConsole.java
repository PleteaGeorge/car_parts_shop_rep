package org.example.ui;

import org.example.config.ui.IFunctor;
import org.example.config.ui.UserConsoleConfiguration;
import org.hibernate.Session;

import java.util.Map;
import java.util.Scanner;

public class UserConsole {
  private static UserConsole instance;
  private final Scanner scanner;
  private final Map<String, IFunctor> commands;
  private final String exitCommand;
  private final String prompter;

  private UserConsole() {
    scanner = new Scanner(System.in);
    commands = UserConsoleConfiguration.getCommands();
    exitCommand = UserConsoleConfiguration.EXIT_COMMAND;
    prompter = UserConsoleConfiguration.PROMPTER;
  }

  public static UserConsole get() {
    if (null == instance) {
      synchronized (UserConsole.class) {
        if (null == instance) {
          instance = new UserConsole();
        }
      }
    }
    return instance;
  }

  private void showPrompter() {
    System.out.print(prompter + " ");
  }

  public void run(Session session) {
    while (true) {
      showPrompter();
      final String command = scanner.nextLine().trim();
      commands.get(command).execute();
      if (exitCommand.equals(command)) {
        break;
      }
    }
  }
}
