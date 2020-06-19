package app.data;

import app.logic.SelectedDish;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

public class SelectedDishDao extends AbstractFacade<SelectedDish> implements Serializable {
  public SelectedDishDao() {
    super(SelectedDish.class);
  }

  @Override
  protected final EntityManager getEntityManager() {
    return PersistenceManager.createEntityManager();
  }

  public void create(SelectedDish obj) {
    try {
      super.persist(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while creating the SelectedDish.\n\n Error:" + e + "\n\n");
    }
  }

  public void edit(SelectedDish obj) {
    try {
      super.merge(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while editing the SelectedDish.\n\n Error:" + e + "\n\n");
    }
  }

  public void delete(SelectedDish obj) {
    try {
      super.remove(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while deleting the SelectedDish.\n\n Error:" + e + "\n\n");
    }
  }

  public List<SelectedDish> search(int id) {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM SelectedDish obj WHERE obj.id = :id")
        .setParameter("id", id)
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting email = '" + id + "' from table SelectedDish.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }
  
   public SelectedDish exist(int id) {
    EntityManager em = getEntityManager();
    try {
      return (SelectedDish) em.createQuery("SELECT obj FROM SelectedDish obj WHERE obj.id = :id")
        .setParameter("id", id).getSingleResult();
        
    } catch (Exception e) {
      System.out.print("An error occurred while getting email = '" + id + "' from table User.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }

  public List<SelectedDish> getAll() {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM SelectedDish obj")
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting all from table SelectedDish.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }
  
  public List<SelectedDish> searchByBill(int billId) {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM SelectedDish obj WHERE obj.bill.id = :billId")
        .setParameter("billId", billId)
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting BILL ID = '" + billId + "' from table SelectedAdditionalCategory.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }
}