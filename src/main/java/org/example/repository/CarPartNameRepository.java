package org.example.repository;

import org.example.repository.entities.CarPartName;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class CarPartNameRepository implements IRepository{
    private final Session session;
    public CarPartNameRepository(Session session){
        this.session = session;
    }
    @Override
    public void insert(Object carPartName) {
        Transaction transaction = session.beginTransaction();
        session.persist((CarPartName)carPartName);
        transaction.commit();
    }

    @Override
    public void delete(Object carPartName) {
        if(carPartName != null) {
            Transaction transaction = session.beginTransaction();
            session.remove((CarPartName) carPartName);
            transaction.commit();
        }
    }
    public List<CarPartName> findAll(){
        return session.createQuery("SELECT u FROM car_parts_names u", CarPartName.class)
                .getResultList();
    }
    public void update(CarPartName carPartName, String newName){
        if(carPartName != null) {
            if(findAll().stream().map(CarPartName::getId).toList().contains(carPartName.getId())) {
                Transaction transaction = session.beginTransaction();
                carPartName.setName(newName);
                session.persist(carPartName);
                transaction.commit();
            }
        }
    }
}
