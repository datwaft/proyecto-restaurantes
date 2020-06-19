package app.data;

import app.logic.SelectedAdditional;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

public class SelectedAdditionalDao extends AbstractFacade<SelectedAdditional> implements Serializable {
  public SelectedAdditionalDao() {
    super(SelectedAdditional.class);
  }

  @Override
  protected final EntityManager getEntityManager() {
    return PersistenceManager.createEntityManager();
  }

  public void create(SelectedAdditional obj) {
    try {
      super.persist(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while creating the SelectedAdditional.\n\n Error:" + e + "\n\n");
    }
  }

  public void edit(SelectedAdditional obj) {
    try {
      super.merge(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while editing the SelectedAdditional.\n\n Error:" + e + "\n\n");
    }
  }

  public void delete(SelectedAdditional obj) {
    try {
      super.remove(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while deleting the SelectedAdditional.\n\n Error:" + e + "\n\n");
    }
  }

  public List<SelectedAdditional> search(int id) {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM SelectedAdditional obj WHERE obj.id = :id")
        .setParameter("id", id)
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting email = '" + id + "' from table SelectedAdditional.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }
  
   public SelectedAdditional exist(int id) {
    EntityManager em = getEntityManager();
    try {
      return (SelectedAdditional) em.createQuery("SELECT obj FROM SelectedAdditional obj WHERE obj.id = :id")
        .setParameter("id", id).getSingleResult();
        
    } catch (Exception e) {
      System.out.print("An error occurred while getting id = '" + id + "' from table SelectedAdditional.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }

  public List<SelectedAdditional> getAll() {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM SelectedAdditional obj")
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting all from tableSelectedAdditional.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }
  
  public List<SelectedAdditional> searchByBill(int billId) {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM SelectedAdditional obj WHERE obj.selectedAdditionalCategory.selectedDish.bill.id = :billId")
        .setParameter("billId", billId)
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting email = '" + billId + "' from table SelectedAdditional.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }
}