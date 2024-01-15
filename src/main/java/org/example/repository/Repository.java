package org.example.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public abstract class Repository<EntityT> {
    private  Session session;
    private Class<EntityT> entityClass;
    public Repository(Session session, Class<EntityT> entityClass){
        this.session = session;
        this.entityClass = entityClass;
    }
    void insert(Object object){
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.persist(object);
            transaction.commit();
        } catch (Exception e){
            System.out.println(e.getMessage());
            if(transaction != null){
                transaction.rollback();
            }
        }
    }
    void delete(UUID id){
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.remove(session.get(entityClass, id));
            transaction.commit();
        } catch (Exception e){
            System.out.println(e.getMessage());
            if(transaction != null){
                transaction.rollback();
            }
        }
    }
    public EntityT find(UUID id){
        return session.get(entityClass, id);
    }
    public List<EntityT> findAll(){
        return session.createQuery("FROM "+ entityClass.getName(), entityClass)
                .getResultList();
    }
}
