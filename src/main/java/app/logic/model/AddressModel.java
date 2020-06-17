/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.logic.model;

import app.data.AddressDao;

/**
 *
 * @author Mario
 */
public class AddressModel extends AddressDao{
  
  private AddressModel() {
  }
  
  public static AddressModel getInstance() {
    return AddressModelHolder.INSTANCE;
  }
  
  private static class AddressModelHolder {

    private static final AddressModel INSTANCE = new AddressModel();
  }
}
