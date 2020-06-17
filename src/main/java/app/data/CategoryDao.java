package app.data;

import app.logic.Category;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

public class CategoryDao extends AbstractFacade<Category> implements Serializable {
  public CategoryDao() {
    super(Category.class);
  }

  @Override
  protected final EntityManager getEntityManager() {
    return PersistenceManager.createEntityManager();
  }

  public void create(Category obj) {
    try {
      super.persist(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while creating the Category.\n\n Error:" + e + "\n\n");
    }
  }

  public void edit(Category obj) {
    try {
      super.merge(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while editing the Category.\n\n Error:" + e + "\n\n");
    }
  }

  public void delete(Category obj) {
    try {
      super.remove(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while deleting the Category.\n\n Error:" + e + "\n\n");
    }
  }

  public List<Category> search(int id) {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM Category obj WHERE obj.id = :id")
        .setParameter("id", id)
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting id = '" + id + "' from table category.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }
  
  public Category exist(int id) {
    EntityManager em = getEntityManager();
    try {
      return (Category) em.createQuery("SELECT obj FROM Category obj WHERE obj.id = :id")
        .setParameter("id", id).getSingleResult();
        
    } catch (Exception e) {
      System.out.print("An error occurred while getting email = '" + id + "' from table Category.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }

  public List<Category> getAll() {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM Category obj")
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting all from table category.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }
}