package app.data;

import app.logic.Address;
import app.logic.Bill;
import app.logic.Category;
import app.logic.Dish;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

public class BillDao extends AbstractFacade<Bill> implements Serializable {
  public BillDao() {
    super(Bill.class);
  }

  @Override
  protected final EntityManager getEntityManager() {
    return PersistenceManager.createEntityManager();
  }
  
  public void create(Bill obj) {
    try {
      super.persist(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while creating the Bill.\n\n Error:" + e + "\n\n");
    }
  }

  public void edit(Bill obj) {
    try {
      super.merge(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while editing the Bill.\n\n Error:" + e + "\n\n");
    }
  }

  public void delete(Bill obj) {
    try {
      super.remove(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while deleting the Bill.\n\n Error:" + e + "\n\n");
    }
  }
  
  public List<Bill> search(int id) {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM Bill obj WHERE obj.user_id = :id")
        .setParameter("id", id)
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting id's = '" + id + "' from table Bill.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }
  
  public Bill exist(int id) {
    EntityManager em = getEntityManager();
    try {
      return (Bill) em.createQuery("SELECT obj FROM Bill obj WHERE obj.id = :id")
        .setParameter("id", id).getSingleResult();
        
    } catch (Exception e) {
      System.out.print("An error occurred while getting id = '" + id + "' from table Bill.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }

  public List<Bill> getAll() {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM Bill obj")
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting all from table Bill.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }
}