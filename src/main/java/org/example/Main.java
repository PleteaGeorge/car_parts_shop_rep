package org.example;

import org.example.ui.UserConsole;

public class Main {
  public static void main(String[] args) {
    BackendApplication.start();
    UserConsole.run(BackendApplication.getSession());
    BackendApplication.terminate();
  }
}
