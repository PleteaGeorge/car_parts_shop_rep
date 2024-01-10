package org.example.ui;

import org.example.config.ui.IFunctor;
import org.example.config.ui.UserConsoleConfiguration;

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
    exitCommand = "";
    prompter = "";
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

  public void run() {
    while (true) {
      showPrompter();
      final String command = scanner.nextLine().trim();
      commands.get(command).executeProvide();
      if (exitCommand.equals(command)) {
        break;
      }
    }
  }
}
