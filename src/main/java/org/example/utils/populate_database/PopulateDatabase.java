package org.example.utils.populate_database;

import org.example.Application;

public class PopulateDatabase {
  public static void main(String[] args) {
    Application.start();
    //
    ClientsUtility.deleteExisting(Application.getSession());
    ClientsUtility.populate(Application.getSession());
    //
    OffersUtility.deleteExisting(Application.getSession());
    //
    CarPartsUtility.deleteExisting(Application.getSession());
    //
    CarsUtility.deleteExisting(Application.getSession());
    CarsUtility.populate(Application.getSession());
    //
    CarPartsNamesUtility.deleteExisting(Application.getSession());
    CarPartsNamesUtility.populate(Application.getSession());
    //
    CarPartsUtility.populate(Application.getSession());
    //
    Application.terminate();
  }
}
