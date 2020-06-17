/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.logic.model;

import app.data.BillDao;

/**
 *
 * @author Mario
 */
public class BillModel extends BillDao{
  
  private BillModel() {
  }
  
  public static BillModel getInstance() {
    return OrderModelHolder.INSTANCE;
  }
  
  private static class OrderModelHolder {

    private static final BillModel INSTANCE = new BillModel();
  }
}
