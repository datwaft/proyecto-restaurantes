/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.logic.model;

import app.data.SelectedAdditionalCategoryDao;

/**
 *
 * @author Mario
 */
public class SelectedAdditionalCategoryModel extends SelectedAdditionalCategoryDao {
  
  private SelectedAdditionalCategoryModel() {
  }
  
  public static SelectedAdditionalCategoryModel getInstance() {
    return SelectedAdditionalCategoryModelHolder.INSTANCE;
  }
  
  private static class SelectedAdditionalCategoryModelHolder {

    private static final SelectedAdditionalCategoryModel INSTANCE = new SelectedAdditionalCategoryModel();
  }
}
