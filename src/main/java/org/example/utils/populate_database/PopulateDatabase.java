package org.example.utils.populate_database;

import org.example.BackendApplication;

public class PopulateDatabase {
  public static void main(String[] args) {
    BackendApplication.start();
    //
    ClientsUtility.deleteExisting(BackendApplication.getSession());
    ClientsUtility.populate(BackendApplication.getSession());
    //
    OffersUtility.deleteExisting(BackendApplication.getSession());
    //
    CarPartsUtility.deleteExisting(BackendApplication.getSession());
    //
    CarsUtility.deleteExisting(BackendApplication.getSession());
    CarsUtility.populate(BackendApplication.getSession());
    //
    CarPartsNamesUtility.deleteExisting(BackendApplication.getSession());
    CarPartsNamesUtility.populate(BackendApplication.getSession());
    //
    CarPartsUtility.populate(BackendApplication.getSession());
    //
    BackendApplication.terminate();
  }
}
