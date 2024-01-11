package org.example.config.ui;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class UserConsoleConfiguration {
  static {
    commands = new HashMap<>();
    buildCommands();
  }

  @Getter
  private final static Map<String, IFunctor> commands;
  public final static String EXIT_COMMAND = "exit";
  public final static String PROMPTER = " Car Parts Store \ud83d\udE02 >";

  private static void buildCommands() {
    commands.put(EXIT_COMMAND, new IFunctor() {
      @Override
      public void execute() {
      }
    });
  }
}
