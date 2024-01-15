package org.example;

import org.example.config.app.Application;
import org.example.ui.UserConsole;

public class Main {
  public static void main(String[] args) {
    Application.start();
    UserConsole.run(Application.getSession());
    Application.terminate();
  }
}
