package app.data;

import app.logic.Address;
import app.logic.AdditionalCategory;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

public class AdditionalCategoryDao extends AbstractFacade<AdditionalCategory> implements Serializable {
  public AdditionalCategoryDao() {
    super(AdditionalCategory.class);
  }

  @Override
  protected final EntityManager getEntityManager() {
    return PersistenceManager.createEntityManager();
  }
  
  public void create(AdditionalCategory obj) {
    try {
      super.persist(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while creating the AdditionalCategory.\n\n Error:" + e + "\n\n");
    }
  }

  public void edit(AdditionalCategory obj) {
    try {
      super.merge(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while editing the AdditionalCategory.\n\n Error:" + e + "\n\n");
    }
  }

  public void delete(AdditionalCategory obj) {
    try {
      super.remove(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while deleting the AdditionalCategory.\n\n Error:" + e + "\n\n");
    }
  }
  
  public List<AdditionalCategory> search(int id) {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM Dish obj WHERE obj.id = :id")
        .setParameter("id", id)
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting id's = '" + id + "' from table AdditionalCategory.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }
  
  public AdditionalCategory exist(int id) {
    EntityManager em = getEntityManager();
    try {
      return (AdditionalCategory) em.createQuery("SELECT obj FROM AdditionalCategory obj WHERE obj.id = :id")
        .setParameter("id", id).getSingleResult();
        
    } catch (Exception e) {
      System.out.print("An error occurred while getting AdditionalCategory = '" + id + "' from table User.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }

  public List<AdditionalCategory> getAll() {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM Additional obj")
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting all from table AdditionalCategory.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }
  
  public List<AdditionalCategory> searchByDish(int dishId) {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM AdditionalCategory obj WHERE obj.dish.id = :dishId")
        .setParameter("dishId", dishId)
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting AdditionalCategory by dish ID = '" + dishId + "' from table AdditionalCategory.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }
}