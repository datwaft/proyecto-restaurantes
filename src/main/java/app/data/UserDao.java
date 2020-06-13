package app.data;

import app.logic.User;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

public class UserDao extends AbstractFacade<User> implements Serializable {
  public UserDao() {
    super(User.class);
  }

  @Override
  protected final EntityManager getEntityManager() {
    return PersistenceManager.createEntityManager();
  }

  public void create(User obj) {
    try {
      super.persist(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while creating the User.\n\n Error:" + e + "\n\n");
    }
  }

  public void edit(User obj) {
    try {
      super.merge(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while editing the User.\n\n Error:" + e + "\n\n");
    }
  }

  public void delete(User obj) {
    try {
      super.remove(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while deleting the User.\n\n Error:" + e + "\n\n");
    }
  }

  public List<User> search(String email) {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM User obj WHERE obj.email = :email")
        .setParameter("email", email)
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting email = '" + email + "' from table User.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }
  
   public User exist(String email) {
    EntityManager em = getEntityManager();
    try {
      return (User) em.createQuery("SELECT obj FROM User obj WHERE obj.email = :email")
        .setParameter("email", email).getSingleResult();
        
    } catch (Exception e) {
      System.out.print("An error occurred while getting email = '" + email + "' from table User.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }

  public List<User> getAll() {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM User obj")
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting all from table User.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }
}