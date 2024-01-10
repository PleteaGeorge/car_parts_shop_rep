package org.example.config.ui;

public interface IFunctor {
  default void execute() {
  }

  default Object executeProvide() {
    return null;
  }
}
