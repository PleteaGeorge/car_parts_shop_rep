package org.example.config.ui;

import org.hibernate.Session;

@FunctionalInterface
public interface Functor {
    void execute (Session session);
}
