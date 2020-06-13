/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.logic.model;

import app.data.AdditionalCategoryDao;
import app.logic.AdditionalCategory;

/**
 *
 * @author Mario
 */
public class AdditionalCategoryModel extends AdditionalCategoryDao{
  
  private AdditionalCategoryModel() {
  }
  
  public static AdditionalCategoryModel getInstance() {
    return AdditionalCategoryModelHolder.INSTANCE;
  }
  
  private static class AdditionalCategoryModelHolder {

    private static final AdditionalCategoryModel INSTANCE = new AdditionalCategoryModel();
  }
}
