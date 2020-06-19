/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.logic.model;

import app.data.SelectedAdditionalDao;

/**
 *
 * @author Mario
 */
public class SelectedAdditionalModel extends SelectedAdditionalDao{
  
  private SelectedAdditionalModel() {
  }
  
  public static SelectedAdditionalModel getInstance() {
    return SelectedAdditionalModelHolder.INSTANCE;
  }
  
  private static class SelectedAdditionalModelHolder {

    private static final SelectedAdditionalModel INSTANCE = new SelectedAdditionalModel();
  }
}
