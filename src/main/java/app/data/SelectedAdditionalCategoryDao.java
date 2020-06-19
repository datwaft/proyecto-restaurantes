package app.data;

import app.logic.SelectedAdditionalCategory;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

public class SelectedAdditionalCategoryDao extends AbstractFacade<SelectedAdditionalCategory> implements Serializable {
  public SelectedAdditionalCategoryDao() {
    super(SelectedAdditionalCategory.class);
  }

  @Override
  protected final EntityManager getEntityManager() {
    return PersistenceManager.createEntityManager();
  }

  public void create(SelectedAdditionalCategory obj) {
    try {
      super.persist(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while creating the SelectedAdditionalCategory.\n\n Error:" + e + "\n\n");
    }
  }

  public void edit(SelectedAdditionalCategory obj) {
    try {
      super.merge(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while editing the SelectedAdditionalCategory.\n\n Error:" + e + "\n\n");
    }
  }

  public void delete(SelectedAdditionalCategory obj) {
    try {
      super.remove(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while deleting the SelectedAdditionalCategory.\n\n Error:" + e + "\n\n");
    }
  }

  public List<SelectedAdditionalCategory> search(int id) {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM SelectedAdditionalCategory obj WHERE obj.id = :id")
        .setParameter("id", id)
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting email = '" + id + "' from table SelectedAdditionalCategory.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }
  
   public SelectedAdditionalCategory exist(int id) {
    EntityManager em = getEntityManager();
    try {
      return (SelectedAdditionalCategory) em.createQuery("SELECT obj FROM SelectedAdditionalCategory obj WHERE obj.id = :id")
        .setParameter("id", id).getSingleResult();
        
    } catch (Exception e) {
      System.out.print("An error occurred while getting email = '" + id + "' from table SelectedAdditionalCategory.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }

  public List<SelectedAdditionalCategory> getAll() {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM SelectedAdditionalCategory obj")
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting all from table SelectedAdditionalCategory.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }
  
  public List<SelectedAdditionalCategory> searchByBill(int billId) {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM SelectedAdditionalCategory obj WHERE obj.selectedDish.bill.id = :billId")
        .setParameter("billId", billId)
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting email = '" + billId + "' from table SelectedAdditionalCategory.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }
}