/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.logic.model;

import app.data.AdditionalDao;
/**
 *
 * @author Mario
 */
public class AdditionalModel extends AdditionalDao{
  
  private AdditionalModel() {
  }
  
  public static AdditionalModel getInstance() {
    return AdditionalModelHolder.INSTANCE;
  }
  
  private static class AdditionalModelHolder {

    private static final AdditionalModel INSTANCE = new AdditionalModel();
  }
}
