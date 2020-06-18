package app.data;

import app.logic.Address;
import app.logic.Category;
import app.logic.Dish;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

public class AddressDao extends AbstractFacade<Address> implements Serializable {
  public AddressDao() {
    super(Address.class);
  }

  @Override
  protected final EntityManager getEntityManager() {
    return PersistenceManager.createEntityManager();
  }
  
  public void create(Address obj) {
    try {
      super.persist(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while creating the Address.\n\n Error:" + e + "\n\n");
    }
  }

  public void edit(Address obj) {
    try {
      super.merge(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while editing the Address.\n\n Error:" + e + "\n\n");
    }
  }

  public void delete(Address obj) {
    try {
      super.remove(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while deleting the Address.\n\n Error:" + e + "\n\n");
    }
  }
  
  public List<Address> search(int id) {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM Dish obj WHERE obj.user_id = :id")
        .setParameter("id", id)
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting id's = '" + id + "' from table Dish.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }

  public List<Address> getAll() {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM Address obj")
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting all from table Address.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }
  
  public List<Address> getAll(String email) {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM Address obj where obj.user.email = :email").setParameter("email", email)
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting all from table Address.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }
}