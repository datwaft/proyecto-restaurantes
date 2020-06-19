package app.data;

import app.logic.Address;
import app.logic.Additional;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

public class AdditionalDao extends AbstractFacade<Additional> implements Serializable {
  public AdditionalDao() {
    super(Additional.class);
  }

  @Override
  protected final EntityManager getEntityManager() {
    return PersistenceManager.createEntityManager();
  }
  
  public void create(Additional obj) {
    try {
      super.persist(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while creating the Additional.\n\n Error:" + e + "\n\n");
    }
  }

  public void edit(Additional obj) {
    try {
      super.merge(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while editing the Additional.\n\n Error:" + e + "\n\n");
    }
  }

  public void delete(Additional obj) {
    try {
      super.remove(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while deleting the Additional.\n\n Error:" + e + "\n\n");
    }
  }
  
  public List<Additional> search(int id) {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM Dish obj WHERE obj.id = :id")
        .setParameter("id", id)
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting id's = '" + id + "' from table Additional.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }

  public List<Additional> getAll() {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM Additional obj")
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting all from table Additional.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }
  public List<Additional> searchByDish(int dishId) {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM Additional obj WHERE obj.additionalCategory.dish.id = :dishId")
        .setParameter("dishId", dishId)
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting additional by dish ID = '" + dishId + "' from table additional.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }
  
}