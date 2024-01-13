package org.example.utils.populate_database;

import org.example.config.app.Application;

public class PopulateDatabase {
  public static void main(String[] args) {
    Application.start();
    //
    PopulateClients.deleteExisting(Application.getSession());
    PopulateClients.populate(Application.getSession());
    //
    PopulateCarParts.deleteExisting(Application.getSession());
    //
    PopulateCars.deleteExisting(Application.getSession());
    PopulateCars.populate(Application.getSession());
    //
    PopulateCarPartsNames.deleteExisting(Application.getSession());
    PopulateCarPartsNames.populate(Application.getSession());
    //
    PopulateCarParts.populate(Application.getSession());
    //
    Application.terminate();
  }
}
