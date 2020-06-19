/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.logic.model;

import app.data.SelectedDishDao;

/**
 *
 * @author Mario
 */
public class SelectedDishModel extends SelectedDishDao {
  
  private SelectedDishModel() {
  }
  
  public static SelectedDishModel getInstance() {
    return SelectedDishModelHolder.INSTANCE;
  }
  
  private static class SelectedDishModelHolder {

    private static final SelectedDishModel INSTANCE = new SelectedDishModel();
  }
}
