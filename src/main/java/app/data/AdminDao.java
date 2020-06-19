package app.data;

import app.logic.Admin;
import app.logic.User;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

public class AdminDao extends AbstractFacade<Admin> implements Serializable {
  public AdminDao() {
    super(Admin.class);
  }

  @Override
  protected final EntityManager getEntityManager() {
    return PersistenceManager.createEntityManager();
  }

  public void create(Admin obj) {
    try {
      super.persist(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while creating the Admin.\n\n Error:" + e + "\n\n");
    }
  }

  public void edit(Admin obj) {
    try {
      super.merge(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while editing the Admin.\n\n Error:" + e + "\n\n");
    }
  }

  public void delete(Admin obj) {
    try {
      super.remove(obj);
    } catch (PersistenceException e) {
      System.out.print("An error occurred while deleting the Admin.\n\n Error:" + e + "\n\n");
    }
  }

  public List<Admin> search(String email) {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM User obj WHERE obj.email = :email")
        .setParameter("email", email)
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting email = '" + email + "' from table Admin.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }
  
   public Admin exist(String username) {
    EntityManager em = getEntityManager();
    try {
      return (Admin) em.createQuery("SELECT obj FROM Admin obj WHERE obj.username = :username")
        .setParameter("username", username).getSingleResult();
        
    } catch (Exception e) {
      System.out.print("An error occurred while getting Username = '" + username + "' from table User.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }

  public List<Admin> getAll() {
    EntityManager em = getEntityManager();
    try {
      return em.createQuery("SELECT obj FROM Admin obj")
        .getResultList();
    } catch (Exception e) {
      System.out.print("An error occurred while getting all from table Admin.\n\n Error:" + e + "\n\n");
      return null;
    } finally {
      em.close();
    }
  }
}