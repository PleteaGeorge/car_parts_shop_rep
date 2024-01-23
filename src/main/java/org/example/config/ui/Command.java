package org.example.config.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Command {
  private final String command;

  @Override
  public boolean equals(Object other) {
    if (null == other) {
      return false;
    }
    if (this == other) {
      return true;
    }
    if (getClass() != other.getClass()) {
      return false;
    }
    return command.equalsIgnoreCase(((Command)other).getCommand());
  }

  @Override
  public int hashCode() {
    return (null == command) ? 0 : command.toLowerCase().hashCode();
  }
}
