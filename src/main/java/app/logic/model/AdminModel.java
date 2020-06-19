/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.logic.model;

import app.data.AdminDao;

/**
 *
 * @author Mario
 */
public class AdminModel extends AdminDao{
  
  private AdminModel() {
  }
  
  public static AdminModel getInstance() {
    return AdminModelHolder.INSTANCE;
  }
  
  private static class AdminModelHolder {

    private static final AdminModel INSTANCE = new AdminModel();
  }
}
