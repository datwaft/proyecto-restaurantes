package app.data;

import app.logic.Category;
import app.logic.Dish;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

public class DishDao extends AbstractFacade<Dish> implements Serializable {
  public DishDao() {
    super(Dish.class);
  }

  @Override
  protected final EntityManager getEntityManager() {
    return PersistenceManager.createEntityManager();
  }
  
  public void create(Dish obj) {
    try {
      super.persist(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while creating the Dish.\n\n Error:" + e + "\n\n");
    }
  }

  public void edit(Dish obj) {
    try {
      super.merge(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while editing the Dish.\n\n Error:" + e + "\n\n");
    }
  }

  public void delete(Dish obj) {
    try {
      super.remove(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while deleting the Dish.\n\n Error:" + e + "\n\n");
    }
  }

  public List<Dish> search(int id) {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM Dish obj WHERE obj.id = :id")
        .setParameter("id", id)
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting email = '" + id + "' from table Dish.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }

  public List<Dish> getAll() {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM Dish obj")
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting all from table dish.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }
}